package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User 表 Data 层
 * 
 * @author
 */
public interface SysUserMapper
{
    /**
     * 根据条件分页查询User 列表
     * 
     * @param sysUser User 信息
     * @return User 信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 通过User 名查询User
     * 
     * @param userName User 名
     * @return User 对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过User ID查询User
     * 
     * @param userId User ID
     * @return User 对象信息
     */
    public SysUser selectUserById(Long userId);

    /**
     * Add User 信息
     * 
     * @param user User 信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     *  Modify  User 信息
     * 
     * @param user User 信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     *  Modify  User 头像
     * 
     * @param userName User 名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * Reset  User 密码
     * 
     * @param userName User 名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过User ID Delete User
     * 
     * @param userId User ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量 Delete User 信息
     * 
     * @param userIds 需要 Delete 的User ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 校验User Name是否唯一
     * 
     * @param userName User Name
     * @return 结果
     */
    public int checkUserNameUnique(String userName);

    /**
     * 校验Tele-Number是否唯一
     *
     * @param phonenumber Tele-Number
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email User 邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);
}
