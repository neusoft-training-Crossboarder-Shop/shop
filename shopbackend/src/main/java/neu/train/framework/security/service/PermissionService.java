package neu.train.framework.security.service;

import neu.train.common.utils.ServletUtils;
import neu.train.common.utils.StringUtils;
import neu.train.framework.security.LoginUser;
import neu.train.project.system.domain.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 *  RuoYi首创 自定义Authority 实现，ss取自SpringSecurity首字母
 * 
 * @author
 */
@Service("ss")
public class PermissionService
{
    /** 所有Authority Signal   */
    private static final String ALL_PERMISSION = "*:*:*";

    /** 管理员RoleAuthority Signal   */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    private TokenService tokenService;

    /**
     * 验证User 是否具备某Authority
     * 
     * @param permission Authority Code串
     * @return User 是否具备某Authority
     */
    public boolean hasPermi(String permission)
    {
        if (StringUtils.isEmpty(permission))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
        {
            return false;
        }
        return hasPermissions(loginUser.getPermissions(), permission);
    }

    /**
     * 验证User 是否不具备某Authority ，与 hasPermi逻辑相反
     *
     * @param permission Authority Code串
     * @return User 是否不具备某Authority
     */
    public boolean lacksPermi(String permission)
    {
        return hasPermi(permission) != true;
    }

    /**
     * 验证User 是否具有以下任意一个Authority
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符's Authority列表
     * @return User 是否具有以下任意一个Authority
     */
    public boolean hasAnyPermi(String permissions)
    {
        if (StringUtils.isEmpty(permissions))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
        {
            return false;
        }
        Set<String> authorities = loginUser.getPermissions();
        for (String permission : permissions.split(PERMISSION_DELIMETER))
        {
            if (permission != null && hasPermissions(authorities, permission))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断User 是否拥有某个Role
     * 
     * @param role RoleCode串
     * @return User 是否具备某Role
     */
    public boolean hasRole(String role)
    {
        if (StringUtils.isEmpty(role))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles()))
        {
            return false;
        }
        for (SysRole sysRole : loginUser.getUser().getRoles())
        {
            String roleKey = sysRole.getRoleKey();
            if (SUPER_ADMIN.contains(roleKey) || roleKey.contains(StringUtils.trim(role)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证User 是否不具备某Role，与 isRole逻辑相反。
     *
     * @param role RoleName
     * @return User 是否不具备某Role
     */
    public boolean lacksRole(String role)
    {
        return hasRole(role) != true;
    }

    /**
     * 验证User 是否具有以下任意一个Role
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的Role列表
     * @return User 是否具有以下任意一个Role
     */
    public boolean hasAnyRoles(String roles)
    {
        if (StringUtils.isEmpty(roles))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles()))
        {
            return false;
        }
        for (String role : roles.split(ROLE_DELIMETER))
        {
            if (hasRole(role))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含Authority
     * 
     * @param permissions Authority 列表
     * @param permission Authority Code串
     * @return User 是否具备某Authority
     */
    private boolean hasPermissions(Set<String> permissions, String permission)
    {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }
}
