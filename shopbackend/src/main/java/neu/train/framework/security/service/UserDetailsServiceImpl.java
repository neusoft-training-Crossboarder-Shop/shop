package neu.train.framework.security.service;

import neu.train.common.enums.UserStatus;
import neu.train.common.exception.BaseException;
import neu.train.common.utils.StringUtils;
import neu.train.framework.security.LoginUser;
import neu.train.project.system.domain.SysUser;
import neu.train.project.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User 验证处理
 *
 * @author
 */
@Service("MyUserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录User ：{} 不存在.", username);
            throw new UsernameNotFoundException("登录User ：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录User ：{} Already be  Delete .", username);
            throw new BaseException("对不起，您的账号：" + username + " Already be  Delete ");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录User ：{} Already be 停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " Already停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
