package neu.train.framework.web.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree基类
 * 
 * @author
 */
public class TreeEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 父Menu Name */
    private String parentName;

    /** 父Menu ID */
    private Long parentId;

    /** Sequence */
    private Integer orderNum;

    /** 祖级列表 */
    private String ancestors;

    /** 子部门 */
    private List<?> children = new ArrayList<>();

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    public List<?> getChildren()
    {
        return children;
    }

    public void setChildren(List<?> children)
    {
        this.children = children;
    }
}
