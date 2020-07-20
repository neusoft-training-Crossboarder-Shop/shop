package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Menu 表 Data 层
 * 
 * @author
 */
public interface SysMenuMapper
{
    /**
     * 查询系统Menu 列表
     * 
     * @param menu Menu 信息
     * @return Menu 列表
     */
    public List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据User 所有Authority
     * 
     * @return Authority 列表
     */
    public List<String> selectMenuPerms();

    /**
     * 根据User 查询系统Menu 列表
     * 
     * @param menu Menu 信息
     * @return Menu 列表
     */
    public List<SysMenu> selectMenuListByUserId(SysMenu menu);

    /**
     * 根据User ID查询Authority
     * 
     * @param userId User ID
     * @return Authority 列表
     */
    public List<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据User ID查询Menu
     * 
     * @return Menu 列表
     */
    public List<SysMenu> selectMenuTreeAll();

    /**
     * 根据User ID查询Menu
     * 

     * @return Menu 列表
     */
    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 根据RoleID查询Menu 树信息
     * 
     * @param roleId RoleID
     * @return 选中Menu 列表
     */
    public List<Integer> selectMenuListByRoleId(Long roleId);

    /**
     * 根据Menu ID查询信息
     * 
     * @param menuId Menu ID
     * @return Menu 信息
     */
    public SysMenu selectMenuById(Long menuId);

    /**
     * 是否存在Menu 子节点
     * 
     * @param menuId Menu ID
     * @return 结果
     */
    public int hasChildByMenuId(Long menuId);

    /**
     * Add Menu 信息
     * 
     * @param menu Menu 信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     *  Modify  Menu 信息
     * 
     * @param menu Menu 信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);

    /**
     *  Delete Menu 管理信息
     * 
     * @param menuId Menu ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 校验Menu Name是否唯一
     * 
     * @param menuName Menu Name
     * @param parentId 父Menu ID
     * @return 结果
     */
    public SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
