package neu.train.project.system.mapper;

import neu.train.project.system.domain.SysRoleMenu;

import java.util.List;

/**
 * Role与Menu 关联表 Data 层
 * 
 * @author
 */
public interface SysRoleMenuMapper
{
    /**
     * 查询Menu 使用数量
     * 
     * @param menuId Menu ID
     * @return 结果
     */
    public int checkMenuExistRole(Long menuId);

    /**
     * 通过RoleID Delete Role和Menu 关联
     * 
     * @param roleId RoleID
     * @return 结果
     */
    public int deleteRoleMenuByRoleId(Long roleId);

    /**
     * 批量Add RoleMenu 信息
     * 
     * @param roleMenuList RoleMenu 列表
     * @return 结果
     */
    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
