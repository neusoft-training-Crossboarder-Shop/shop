package neu.train.project.system.controller;

import neu.train.common.utils.SecurityUtils;
//import neu.train.common.utils.poi.ExcelUtil;
//import neu.train.framework.aspectj.lang.annotation.Log;
//import neu.train.framework.aspectj.lang.enums.BusinessType;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.system.domain.SysDictData;
import neu.train.project.system.service.ISysDictDataService;
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
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }



    /**
     * 查询Dict   Data 详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode)
    {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据Dict   Type 查询Dict   Data 信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictTypeService.selectDictDataByType(dictType));
    }

    /**
     * Add Dict   Type
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
//    @Log(title = "Dict   Data ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     *  Modify  保存Dict   Type
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
//    @Log(title = "Dict   Data ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     *  Delete Dict   Type
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
//    @Log(title = "Dict   Type ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
