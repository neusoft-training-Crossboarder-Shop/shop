package neu.train.project.system.controller;

import neu.train.common.constant.UserConstants;
import neu.train.common.utils.SecurityUtils;
//import neu.train.common.utils.poi.ExcelUtil;
//import neu.train.framework.aspectj.lang.annotation.Log;
//import neu.train.framework.aspectj.lang.enums.BusinessType;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.system.domain.SysRole;
import neu.train.project.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Role信息
 * 
 * @author
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController
{
    @Autowired
    private ISysRoleService roleService;

    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }



    /**
     * 根据RoleID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId)
    {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * Add Role
     */

    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysRole role)
    {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("Add Role'" + role.getRoleName() + "'Fail，RoleNameAlready存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("Add Role'" + role.getRoleName() + "'Fail，RoleAuthority Already存在");
        }
        role.setCreateBy(SecurityUtils.getUsername());
        return toAjax(roleService.insertRole(role));

    }

    /**
     *  Modify  保存Role
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error(" Modify  Role'" + role.getRoleName() + "'Fail，RoleNameAlready存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error(" Modify  Role'" + role.getRoleName() + "'Fail，RoleAuthority Already存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRole(role));
    }

    /**
     *  Modify  保存 Data Authority
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * Status Modify
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     *  Delete Role
     */
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取Role Selection  框列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        return AjaxResult.success(roleService.selectRoleAll());
    }
}