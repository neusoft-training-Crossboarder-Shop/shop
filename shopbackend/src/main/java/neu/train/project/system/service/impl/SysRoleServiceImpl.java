package neu.train.project.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import neu.train.common.constant.UserConstants;
import neu.train.common.exception.CustomException;
import neu.train.common.utils.StringUtils;
import neu.train.common.utils.spring.SpringUtils;
import neu.train.project.system.domain.SysRole;
import neu.train.project.system.domain.SysRoleMenu;
import neu.train.project.system.mapper.SysRoleMapper;
import neu.train.project.system.mapper.SysRoleMenuMapper;
import neu.train.project.system.mapper.SysUserRoleMapper;
import neu.train.project.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Role 业务层处理
 * 
 * @author
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService
{
    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

//    @Autowired
//    private SysRoleDeptMapper roleDeptMapper;

    /**
     * 根据条件分页查询RoleData
     * 
     * @param role Role信息
     * @return RoleData 集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据User ID查询Authority
     * 
     * @param userId User ID
     * @return Authority 列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId)
    {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 查询所有Role
     * 
     * @return Role列表
     */
    @Override
    public List<SysRole> selectRoleAll()
    {
        return SpringUtils.getAopProxy(this).selectRoleList(new SysRole());
    }

    /**
     * 根据User ID获取Role Selection  框列表
     * 
     * @param userId User ID
     * @return 选中RoleID列表
     */
    @Override
    public List<Integer> selectRoleListByUserId(Long userId)
    {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * 通过RoleID查询Role
     * 
     * @param roleId RoleID
     * @return Role对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 校验RoleName是否唯一
     * 
     * @param role Role信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验RoleAuthority 是否唯一
     * 
     * @param role Role信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验Role是否允许Operation
     * 
     * @param role Role信息
     */
    @Override
    public void checkRoleAllowed(SysRole role)
    {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
        {
            throw new CustomException("不允许Operation超级管理员Role");
        }
    }

    /**
     * 通过RoleID查询Role使用数量
     * 
     * @param roleId RoleID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId)
    {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * Add 保存Role信息
     * 
     * @param role Role信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(SysRole role)
    {
        // Add Role信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     *  Modify  保存Role信息
     * 
     * @param role Role信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(SysRole role)
    {
        //  Modify  Role信息
        roleMapper.updateRole(role);
        //  Delete Role与Menu 关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     *  Modify  RoleStatus
     * 
     * @param role Role信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(SysRole role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     *  Modify   Data Authority信息
     * 
     * @param role Role信息
     * @return 结果
     */
    @Override
    @Transactional
    public int authDataScope(SysRole role)
    {
        //  Modify  Role信息
        roleMapper.updateRole(role);
        //  Delete Role与部门关联
//        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // Add Role和部门信息（ Data Authority）
        return insertRoleDept(role);
    }

    /**
     * Add RoleMenu 信息
     * 
     * @param role Role对象
     */
    public int insertRoleMenu(SysRole role)
    {
        int rows = 1;
        // Add User 与Role管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * Add Role部门信息( Data Authority)
     *
     * @param role Role对象
     */
    public int insertRoleDept(SysRole role)
    {
        int rows = 1;
//        // Add Role与部门（ Data Authority）管理
//        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
//        for (Long deptId : role.getDeptIds())
//        {
//            SysRoleDept rd = new SysRoleDept();
//            rd.setRoleId(role.getRoleId());
//            rd.setDeptId(deptId);
//            list.add(rd);
//        }
//        if (list.size() > 0)
//        {
//            rows = roleDeptMapper.batchRoleDept(list);
//        }
        return rows;
    }

    /**
     * 通过RoleID Delete Role
     * 
     * @param roleId RoleID
     * @return 结果
     */
    @Override
    public int deleteRoleById(Long roleId)
    {
        return roleMapper.deleteRoleById(roleId);
    }

    /**
     * 批量 Delete Role信息
     * 
     * @param roleIds 需要 Delete 的RoleID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(Long[] roleIds)
    {
        for (Long roleId : roleIds)
        {
            checkRoleAllowed(new SysRole(roleId));
            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new CustomException(String.format("%1$sAlready分配,不能 Delete ", role.getRoleName()));
            }
        }
        return roleMapper.deleteRoleByIds(roleIds);
    }
}
