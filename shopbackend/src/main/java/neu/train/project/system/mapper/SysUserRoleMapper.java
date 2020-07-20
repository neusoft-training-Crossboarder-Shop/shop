package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User 与Role关联表 Data 层
 * 
 * @author
 */
public interface SysUserRoleMapper
{
    /**
     * 通过User ID Delete User 和Role关联
     * 
     * @param userId User ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 批量 Delete User 和Role关联
     * 
     * @param ids 需要 Delete 的Data ID
     * @return 结果
     */
    public int deleteUserRole(Long[] ids);

    /**
     * 通过RoleID查询Role使用数量
     * 
     * @param roleId RoleID
     * @return 结果
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * 批量Add User Role信息
     * 
     * @param userRoleList User Role列表
     * @return 结果
     */
    public int batchUserRole(List<SysUserRole> userRoleList);

    /**
     *  Delete User 和Role关联信息
     * 
     * @param userRole User 和Role关联信息
     * @return 结果
     */
    public int deleteUserRoleInfo(SysUserRole userRole);

    /**
     * 批量取消授权User Role
     * 
     * @param roleId RoleID
     * @param userIds 需要 Delete 的User Data ID
     * @return 结果
     */
    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}
