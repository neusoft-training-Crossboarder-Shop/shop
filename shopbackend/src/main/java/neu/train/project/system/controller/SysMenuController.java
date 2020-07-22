package neu.train.project.system.controller;

import neu.train.common.constant.Constants;
import neu.train.common.constant.UserConstants;
import neu.train.common.utils.SecurityUtils;
import neu.train.common.utils.ServletUtils;
import neu.train.common.utils.StringUtils;
//import neu.train.framework.aspectj.lang.annotation.Log;
//import neu.train.framework.aspectj.lang.enums.BusinessType;
import neu.train.framework.security.LoginUser;
import neu.train.framework.security.service.TokenService;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.system.domain.SysMenu;
import neu.train.project.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Menu 信息
 * 
 * @author
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取Menu 列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return AjaxResult.success(menus);
    }

    /**
     * 根据Menu ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId)
    {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    /**
     * 获取Menu Pull 树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应RoleMenu 列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysMenu> menus = menuService.selectMenuList(loginUser.getUser().getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * Add Menu
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
//    @Log(title = "Menu 管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("Add Menu '" + menu.getMenuName() + "'Fail，Menu NameAlready存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame())
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS))
        {
            return AjaxResult.error("Add Menu '" + menu.getMenuName() + "'Fail，地址必须以http(s)://开头");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     *  Modify  Menu
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error(" Modify  Menu '" + menu.getMenuName() + "'Fail，Menu NameAlready存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame())
                && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS))
        {
            return AjaxResult.error("Add Menu '" + menu.getMenuName() + "'Fail，地址必须以http(s)://开头");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     *  Delete Menu
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
//    @Log(title = "Menu 管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.hasChildByMenuId(menuId))
        {
            return AjaxResult.error("存在子Menu ,不允许 Delete ");
        }
        if (menuService.checkMenuExistRole(menuId))
        {
            return AjaxResult.error("Menu Already分配,不允许 Delete ");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }
}