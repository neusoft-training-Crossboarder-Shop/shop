package neu.train.framework.security.service;

import neu.train.common.constant.Constants;
import neu.train.common.exception.CustomException;
import neu.train.common.exception.user.CaptchaException;
import neu.train.common.exception.user.CaptchaExpireException;
import neu.train.common.exception.user.UserPasswordNotMatchException;
import neu.train.framework.redis.RedisCache;
import neu.train.framework.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 * 
 * @author
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     * 
     * @param username User 名
     * @param password 密码
     * @param captcha Validation Code
     * @param uuid 唯一Signal
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        //uuid 在客户端就Already经生成了 code是Validation Code  uuid 在生成Validation Code 时产生
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        //为该User 生成Validation Code 的时候 就Already经缓存了这个Validation Code  uuid
        String captcha = redisCache.getCacheObject(verifyKey);

        // Delete 这个缓存的对象
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
//             记录日志
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));

            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // User 验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用    UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        //记录登陆行为
//        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token 并且缓存该User
        return tokenService.createToken(loginUser);
    }
}
