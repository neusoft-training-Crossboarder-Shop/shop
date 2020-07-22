package neu.train.project.system.controller;

import neu.train.common.constant.Constants;
import neu.train.common.constant.UserConstants;
import neu.train.common.utils.SecurityUtils;
import neu.train.common.utils.ServletUtils;
import neu.train.framework.security.LoginBody;
import neu.train.framework.security.LoginUser;
import neu.train.framework.security.service.SysLoginService;
import neu.train.framework.security.service.SysPermissionService;
import neu.train.framework.security.service.TokenService;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.system.domain.SysMenu;
import neu.train.project.system.domain.SysUser;
import neu.train.project.system.service.ISysMenuService;
import neu.train.project.system.service.ISysRoleService;
import neu.train.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录方法
     * 
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 返回一个token作为结果
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/register")
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("Add User '" + user.getUserName() + "'Fail，登录Account Already存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("Add User '" + user.getUserName() + "'Fail，Tele-NumberAlready存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("Add User '" + user.getUserName() + "'Fail，邮箱Account Already存在");
        }
        user.setCreateBy(user.getUserName());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        return toAjax(userService.insertUser(user));
    }

    /**
     * 获取User 信息
     * 
     * @return User 信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // Role集合
        Set<String> roles = permissionService.getRolePermission(user);
        // Authority 集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // User 信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    public  AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
