package neu.train.project.system.service;

import neu.train.framework.web.domain.TreeSelect;
import neu.train.project.system.domain.SysMenu;
import neu.train.project.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * Menu  业务层
 * 
 * @author
 */
public interface ISysMenuService
{
    /**
     * 根据User 查询系统Menu 列表
     * 
     * @param userId User ID
     * @return Menu 列表
     */
    public List<SysMenu> selectMenuList(Long userId);

    /**
     * 根据User 查询系统Menu 列表
     * 
     * @param menu Menu 信息
     * @param userId User ID
     * @return Menu 列表
     */
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * 根据User ID查询Authority
     * 
     * @param userId User ID
     * @return Authority 列表
     */
    public Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据User ID查询Menu 树信息
     * 
     * @param userId User ID
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
     * 构建前端路由所需要的Menu
     * 
     * @param menus Menu 列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<SysMenu> menus);

    /**
     * 构建前端所需要树结构
     * 
     * @param menus Menu 列表
     * @return 树结构列表
     */
    public List<SysMenu> buildMenuTree(List<SysMenu> menus);

    /**
     * 构建前端所需要Pull 树结构
     * 
     * @param menus Menu 列表
     * @return Pull 树结构列表
     */
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

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
     * @return 结果 true 存在 false 不存在
     */
    public boolean hasChildByMenuId(Long menuId);

    /**
     * 查询Menu 是否存在Role
     * 
     * @param menuId Menu ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkMenuExistRole(Long menuId);

    /**
     * Add 保存Menu 信息
     * 
     * @param menu Menu 信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     *  Modify  保存Menu 信息
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
     * @param menu Menu 信息
     * @return 结果
     */
    public String checkMenuNameUnique(SysMenu menu);
}
