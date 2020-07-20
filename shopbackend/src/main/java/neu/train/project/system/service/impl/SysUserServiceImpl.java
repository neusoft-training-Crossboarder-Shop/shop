package neu.train.project.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import neu.train.common.constant.UserConstants;
import neu.train.common.exception.CustomException;
import neu.train.common.utils.SecurityUtils;
import neu.train.common.utils.StringUtils;
import neu.train.project.system.domain.*;
import neu.train.project.system.mapper.*;
import neu.train.project.system.service.ISysConfigService;
import neu.train.project.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User  业务层处理
 * 
 * @author
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

//    @Autowired
//    private SysPostMapper postMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

//    @Autowired
//    private SysUserPostMapper userPostMapper;

    @Autowired
    private ISysConfigService configService;

    /**
     * 根据条件分页查询User 列表
     * 
     * @param user User 信息
     * @return User 信息集合信息
     */
    @Override
//    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过User 名查询User
     * 
     * @param userName User 名
     * @return User 对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName)
    {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过User ID查询User
     * 
     * @param userId User ID
     * @return User 对象信息
     */
    @Override
    public SysUser selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 查询User 所属Role组
     * 
     * @param userName User 名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName)
    {
        List<SysRole> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询User 所属岗位组
     * 
     * @param userName User 名
     * @return 结果
     */
//    @Override
//    public String selectUserPostGroup(String userName)
//    {
//        List<SysPost> list = postMapper.selectPostsByUserName(userName);
//        StringBuffer idsStr = new StringBuffer();
//        for (SysPost post : list)
//        {
//            idsStr.append(post.getPostName()).append(",");
//        }
//        if (StringUtils.isNotEmpty(idsStr.toString()))
//        {
//            return idsStr.substring(0, idsStr.length() - 1);
//        }
//        return idsStr.toString();
//    }

    /**
     * 校验User Name是否唯一
     * 
     * @param userName User Name
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName)
    {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验User Name是否唯一
     *
     * @param user User 信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user User 信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验User 是否允许Operation
     * 
     * @param user User 信息
     */
    @Override
    public void checkUserAllowed(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            throw new CustomException("不允许Operation超级管理员User ");
        }
    }

    /**
     * Add 保存User 信息
     * 
     * @param user User 信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user)
    {
        // Add User 信息
        int rows = userMapper.insertUser(user);
//         Add User 岗位关联
//        insertUserPost(user);
        // Add User 与Role管理
        insertUserRole(user);
        return rows;
    }

    /**
     *  Modify  保存User 信息
     * 
     * @param user User 信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user)
    {
        Long userId = user.getUserId();
        //  Delete User 与Role关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // Add User 与Role管理
        insertUserRole(user);
        //  Delete User 与岗位关联
//        userPostMapper.deleteUserPostByUserId(userId);
        // Add User 与岗位管理
//        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     *  Modify  User Status
     * 
     * @param user User 信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     *  Modify  User 基本信息
     * 
     * @param user User 信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     *  Modify  User 头像
     * 
     * @param userName User 名
     * @param avatar 头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar)
    {
        return userMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * Reset  User 密码
     * 
     * @param user User 信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * Reset  User 密码
     * 
     * @param userName User 名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password)
    {
        return userMapper.resetUserPwd(userName, password);
    }

    /**
     * Add User Role信息
     * 
     * @param user User 对象
     */
    public void insertUserRole(SysUser user)
    {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // Add User 与Role管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles)
            {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0)
            {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

//    /**
//     * Add User 岗位信息
//     *
//     * @param user User 对象
//     */
//    public void insertUserPost(SysUser user)
//    {
//        Long[] posts = user.getPostIds();
//        if (StringUtils.isNotNull(posts))
//        {
//            // Add User 与岗位管理
//            List<SysUserPost> list = new ArrayList<SysUserPost>();
//            for (Long postId : posts)
//            {
//                SysUserPost up = new SysUserPost();
//                up.setUserId(user.getUserId());
//                up.setPostId(postId);
//                list.add(up);
//            }
//            if (list.size() > 0)
//            {
//                userPostMapper.batchUserPost(list);
//            }
//        }
//    }

    /**
     * 通过User ID Delete User
     * 
     * @param userId User ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        //  Delete User 与Role关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        //  Delete User 与岗位表
//        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量 Delete User 信息
     * 
     * @param userIds 需要 Delete 的User ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] userIds)
    {
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        return userMapper.deleteUserByIds(userIds);
    }


}
