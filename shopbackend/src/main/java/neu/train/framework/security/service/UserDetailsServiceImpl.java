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
            log.info("Login User ：{} not exist.", username);
            throw new UsernameNotFoundException("Login User ：" + username + " not exist");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("Login User ：{} Already be  Delete .", username);
            throw new BaseException("Sorry，your Account ：" + username + " Already be  Delete ");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("Login User ：{} Already be forbidden.", username);
            throw new BaseException("Sorry，your Account ：" + username + " Already not used");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
