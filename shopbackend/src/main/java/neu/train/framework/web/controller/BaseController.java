package neu.train.framework.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import neu.train.common.constant.HttpStatus;
import neu.train.common.utils.DateUtils;
import neu.train.common.utils.StringUtils;
import neu.train.common.utils.sql.SqlUtil;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.PageDomain;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.framework.web.page.TableSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用Data 处理
 * 
 * @author
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的Time   格式的Code串，自动转化为DateType
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date Type 转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });

    }

    /**
     * 设置请求分页Data
     */
    protected void startPage()
    {

        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

    }

    /**
     * 响应请求分页Data
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询Success");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return Operation结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
