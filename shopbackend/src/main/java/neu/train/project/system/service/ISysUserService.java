package neu.train.project.system.service;

import neu.train.project.system.domain.SysUser;

import java.util.List;

/**
 * User  业务层
 * 
 * @author
 */
public interface ISysUserService
{
    /**
     * 根据条件分页查询User 列表
     * 
     * @param user User 信息
     * @return User 信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);

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
     * 根据User ID查询User 所属Role组
     * 
     * @param userName User 名
     * @return 结果
     */
    public String selectUserRoleGroup(String userName);

    /**
     * 根据User ID查询User 所属岗位组
     * 
     * @param userName User 名
     * @return 结果
     */
//    public String selectUserPostGroup(String userName);

    /**
     * 校验User Name是否唯一
     * 
     * @param userName User Name
     * @return 结果
     */
    public String checkUserNameUnique(String userName);

    /**
     * 校验Tele-Number是否唯一
     *
     * @param user User 信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user User 信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);

    /**
     * 校验User 是否允许Operation
     * 
     * @param user User 信息
     */
    public void checkUserAllowed(SysUser user);

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
     *  Modify  User Status
     * 
     * @param user User 信息
     * @return 结果
     */
    public int updateUserStatus(SysUser user);

    /**
     *  Modify  User 基本信息
     * 
     * @param user User 信息
     * @return 结果
     */
    public int updateUserProfile(SysUser user);

    /**
     *  Modify  User 头像
     * 
     * @param userName User 名
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateUserAvatar(String userName, String avatar);

    /**
     * Reset  User 密码
     * 
     * @param user User 信息
     * @return 结果
     */
    public int resetPwd(SysUser user);

    /**
     * Reset  User 密码
     * 
     * @param userName User 名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(String userName, String password);

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


}
