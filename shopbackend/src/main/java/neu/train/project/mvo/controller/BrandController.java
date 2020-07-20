package neu.train.project.mvo.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.file.FileUploadUtils;
import neu.train.framework.config.ShopConfig;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.mvo.domain.mvoBrand;
import neu.train.project.mvo.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mvo")
public class BrandController extends BaseController {
    @Autowired
    IBrandService brandService;
    @GetMapping("/brand/list")
    @ApiOperation("try to get brand list")
    public TableDataInfo getBrandList(mvoBrand brand) {
        startPage();
        List<mvoBrand> brandList = brandService.getBrandList(brand);
        return getDataTable(brandList);
    }

    @GetMapping("/brand/all")
    public AjaxResult getBrandList() {
        return AjaxResult.success(brandService.getBrandList());
    }

    @GetMapping("/brand/{id}")
    @ApiOperation("try to get brand by Id")
    public AjaxResult getBrandById(@PathVariable("id") Integer brandId) {
        mvoBrand brand = brandService.selectBrandByBrandId(brandId);
        return AjaxResult.success(brand);
    }

    @PutMapping("/brand")
    @ApiOperation("try to add brand")
    @PreAuthorize("ss.hasPermi('mvo:brand:edit')")
    public AjaxResult updateBrand(@RequestBody mvoBrand brand) {
        brandService.updateBrand(brand);
        return AjaxResult.updateSuccess();
    }

    @PostMapping("/brand")
    @ApiOperation("try to add brand")
    @PreAuthorize("ss.hasPermi('mvo:brand:add')")
    public AjaxResult addBrand(@RequestBody mvoBrand brand) {
        brandService.insertBrand(brand);
        return AjaxResult.insertSuccess();
    }

    @DeleteMapping("/brand/{ids}")
    @ApiOperation("try to add brand")
    @PreAuthorize("ss.hasPermi('mvo:brand:remove')")
    public AjaxResult delStore(@PathVariable("ids")int[] ids ){
        brandService.deleteBrandByIds(ids);
        return AjaxResult.deleteSuccess();
    }

    //    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/brand/image")
    @PreAuthorize("ss.hasPermi('mvo:brand:image:edit')")
    public AjaxResult brandImage(@RequestParam("brandfile") MultipartFile file,@RequestParam("brdId")Integer brdId) throws IOException
    {
        System.out.println(brdId);
        if (!file.isEmpty())
        {
            String brandImageUrl = FileUploadUtils.upload(ShopConfig.getBrandImagePath(), file);
            if (brandService.updateBrandImage(brdId, brandImageUrl)){
                return AjaxResult.updateSuccess();
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }


}
