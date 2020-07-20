package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysRole;

import java.util.List;

/**
 * Role表 Data 层
 * 
 * @author
 */
public interface SysRoleMapper
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
     * @return Role列表
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);

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
     * 根据User ID查询Role
     * 
     * @param userName User 名
     * @return Role列表
     */
    public List<SysRole> selectRolesByUserName(String userName);

    /**
     * 校验RoleName是否唯一
     * 
     * @param roleName RoleName
     * @return Role信息
     */
    public SysRole checkRoleNameUnique(String roleName);

    /**
     * 校验RoleAuthority 是否唯一
     * 
     * @param roleKey RoleAuthority
     * @return Role信息
     */
    public SysRole checkRoleKeyUnique(String roleKey);

    /**
     *  Modify  Role信息
     * 
     * @param role Role信息
     * @return 结果
     */
    public int updateRole(SysRole role);

    /**
     * Add Role信息
     * 
     * @param role Role信息
     * @return 结果
     */
    public int insertRole(SysRole role);

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
