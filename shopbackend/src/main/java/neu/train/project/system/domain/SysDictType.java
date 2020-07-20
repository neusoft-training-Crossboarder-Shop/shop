package neu.train.project.system.domain;

import neu.train.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Dict   Type 表 sys_dict_type
 * 
 * @author
 */
public class SysDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Dict   主键 */
    private Long dictId;

    /** Dict   Name */
    private String dictName;

    /** Dict   Type  */
    private String dictType;

    /** Status（0Normal  1停用） */
    private String status;

    public Long getDictId()
    {
        return dictId;
    }

    public void setDictId(Long dictId)
    {
        this.dictId = dictId;
    }

    @NotBlank(message = "Dict   Name不能为空")
    @Size(min = 0, max = 100, message = "Dict   Type Name长度不能超过100个Code")
    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    @NotBlank(message = "Dict   Type 不能为空")
    @Size(min = 0, max = 100, message = "Dict   Type Type 长度不能超过100个Code")
    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("dictName", getDictName())
            .append("dictType", getDictType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
