package neu.train.project.system.domain;

//import neu.train.framework.aspectj.lang.annotation.Excel;
//import neu.train.framework.aspectj.lang.annotation.Excel.ColumnType;
import neu.train.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Role表 sys_role
 * 
 * @author
 */
public class SysRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** RoleID */
//    @Excel(name = "Role序号", cellType = ColumnType.NUMERIC)
    private Long roleId;

    /** RoleName */
//    @Excel(name = "RoleName")
    private String roleName;

    /** RoleAuthority  */
//    @Excel(name = "RoleAuthority ")
    private String roleKey;

    /** RoleSequence  */
//    @Excel(name = "RoleSequence ")
    private String roleSort;

    /** Data 范围（1：所有 Data Authority；2：自定义 Data Authority；3：本部门 Data Authority；4：本部门及以下 Data Authority） */
//    @Excel(name = "Data 范围", readConverterExp = "1=所有 Data Authority,2=自定义 Data Authority,3=本部门 Data Authority,4=本部门及以下 Data Authority")
    private String dataScope;

    /** RoleStatus（0Normal  1停用） */
//    @Excel(name = "RoleStatus", readConverterExp = "0=Normal ,1=停用")
    private String status;

    /**  Delete 标志（0代表存在 2代表 Delete ） */
    private String delFlag;

    /** User 是否存在此RoleSignal   默认不存在 */
    private boolean flag = false;

    /** Menu 组 */
    private Long[] menuIds;

    /** 部门组（ Data Authority） */
    private Long[] deptIds;

    public SysRole()
    {

    }

    public SysRole(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId)
    {
        return roleId != null && 1L == roleId;
    }

    @NotBlank(message = "RoleName不能为空")
    @Size(min = 0, max = 30, message = "RoleName长度不能超过30个Code")
    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @NotBlank(message = "Authority Code不能为空")
    @Size(min = 0, max = 100, message = "Authority Code长度不能超过100个Code")
    public String getRoleKey()
    {
        return roleKey;
    }

    public void setRoleKey(String roleKey)
    {
        this.roleKey = roleKey;
    }

//    @NotBlank(message = "Sequence不能为空")
    @NotNull(message = "Sequence不能为空")
    public String getRoleSort()
    {
        return roleSort;
    }

    public void setRoleSort(String roleSort)
    {
        this.roleSort = roleSort;
    }

    public String getDataScope()
    {
        return dataScope;
    }

    public void setDataScope(String dataScope)
    {
        this.dataScope = dataScope;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public Long[] getMenuIds()
    {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds)
    {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds()
    {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds)
    {
        this.deptIds = deptIds;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("roleKey", getRoleKey())
            .append("roleSort", getRoleSort())
            .append("dataScope", getDataScope())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
