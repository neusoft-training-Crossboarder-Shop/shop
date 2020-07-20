package neu.train.project.system.service;

import neu.train.project.system.domain.SysRole;

import java.util.List;
import java.util.Set;

/**
 * Role业务层
 * 
 * @author
 */
public interface ISysRoleService
{
    /**
     * 根据条件分页查询RoleData
     * 
     * @param role Role信息
     * @return RoleData 集合信息
     */
    public List<SysRole> selectRoleList(SysRole role);

    /**
     * 根据User ID查询Role
     * 
     * @param userId User ID
     * @return Authority 列表
     */
    public Set<String> selectRolePermissionByUserId(Long userId);

    /**
     * 查询所有Role
     * 
     * @return Role列表
     */
    public List<SysRole> selectRoleAll();

    /**
     * 根据User ID获取Role Selection  框列表
     * 
     * @param userId User ID
     * @return 选中RoleID列表
     */
    public List<Integer> selectRoleListByUserId(Long userId);

    /**
     * 通过RoleID查询Role
     * 
     * @param roleId RoleID
     * @return Role对象信息
     */
    public SysRole selectRoleById(Long roleId);

    /**
     * 校验RoleName是否唯一
     * 
     * @param role Role信息
     * @return 结果
     */
    public String checkRoleNameUnique(SysRole role);

    /**
     * 校验RoleAuthority 是否唯一
     * 
     * @param role Role信息
     * @return 结果
     */
    public String checkRoleKeyUnique(SysRole role);

    /**
     * 校验Role是否允许Operation
     * 
     * @param role Role信息
     */
    public void checkRoleAllowed(SysRole role);

    /**
     * 通过RoleID查询Role使用数量
     * 
     * @param roleId RoleID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * Add 保存Role信息
     * 
     * @param role Role信息
     * @return 结果
     */
    public int insertRole(SysRole role);

    /**
     *  Modify  保存Role信息
     * 
     * @param role Role信息
     * @return 结果
     */
    public int updateRole(SysRole role);

    /**
     *  Modify  RoleStatus
     * 
     * @param role Role信息
     * @return 结果
     */
    public int updateRoleStatus(SysRole role);

    /**
     *  Modify   Data Authority信息
     * 
     * @param role Role信息
     * @return 结果
     */
    public int authDataScope(SysRole role);

    /**
     * 通过RoleID Delete Role
     * 
     * @param roleId RoleID
     * @return 结果
     */
    public int deleteRoleById(Long roleId);

    /**
     * 批量 Delete Role信息
     * 
     * @param roleIds 需要 Delete 的RoleID
     * @return 结果
     */
    public int deleteRoleByIds(Long[] roleIds);
}
