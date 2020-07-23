package neu.train.project.system.controller;

import neu.train.common.constant.UserConstants;
import neu.train.common.utils.SecurityUtils;
//import neu.train.common.utils.poi.ExcelUtil;
//import neu.train.framework.aspectj.lang.annotation.Log;
//import neu.train.framework.aspectj.lang.enums.BusinessType;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.system.domain.SysDictType;
import neu.train.project.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Data Dict   信息
 * 
 * @author
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController
{
    @Autowired
    private ISysDictTypeService dictTypeService;

    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 查询Dict   Type 详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictId}")
    public AjaxResult getInfo(@PathVariable Long dictId)
    {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * Add Dict   Type
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
//    @Log(title = "Dict   Type ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return AjaxResult.error("Add Dict   '" + dict.getDictName() + "'Fail，Dict   Type Already存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     *  Modify  Dict   Type
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
//    @Log(title = "Dict   Type ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return AjaxResult.error(" Modify  Dict   '" + dict.getDictName() + "'Fail，Dict   Type Already存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     *  Delete Dict   Type
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
//    @Log(title = "Dict   Type ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public AjaxResult remove(@PathVariable Long[] dictIds)
    {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 清空缓存
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
//    @Log(title = "Dict   Type ", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clearCache")
    public AjaxResult clearCache()
    {
        dictTypeService.clearCache();
        return AjaxResult.success();
    }

    /**
     * 获取Dict    Selection  框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }
}
