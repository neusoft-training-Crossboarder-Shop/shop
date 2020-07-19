package neu.train.project.mvo.controller;


import neu.train.common.utils.file.FileUploadUtils;
import neu.train.framework.config.ShopConfig;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.mvo.domain.mvoProduct;
import neu.train.project.mvo.domain.mvoProductDescription;
import neu.train.project.mvo.domain.vo.MvoSearchProduct;
import neu.train.project.mvo.service.IMvoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("swagger-resources/mvo/product")
//@RequestMapping("")
public class MvoProductController extends BaseController {

    @Autowired
    IMvoProductService mvoProductService;

    @GetMapping("/mvo/product/list")
    public AjaxResult getProductList(MvoSearchProduct mvoSearchProduct) {
        startPage();
        List<mvoProduct> productList = mvoProductService.getProductList(mvoSearchProduct);
        return AjaxResult.success(getDataTable(productList));
    }

    @PostMapping("/mvo/product")
    public AjaxResult insertProduct(@RequestBody mvoProduct product) {
        mvoProductService.insertProduct(product);
        return AjaxResult.insertSuccess();
    }

    @PutMapping("/mvo/product")
    public AjaxResult updateProduct(@RequestBody mvoProduct product) {
        mvoProductService.updateProduct(product);
        return AjaxResult.updateSuccess();
    }

    @GetMapping("/mvo/product/{proId}")
    public AjaxResult getProductById(@PathVariable("proId")Integer proId ){
        return AjaxResult.success(mvoProductService.getProductByProId(proId));
    }

    @DeleteMapping("/mvo/product/{ids}")
    public AjaxResult deleteProductById(@PathVariable("ids")int[] id) {
        mvoProductService.deleteProductByIds(id);
        return AjaxResult.deleteSuccess();
    }

    @GetMapping("/mvo/product/description/{proId}")
    public AjaxResult getDescriptionByProId(@PathVariable("proId")Integer proId) {
        return AjaxResult.success(mvoProductService.getDescriptionByProId(proId));
    }

    @GetMapping("/mvo/product/image/{proId}")
    public AjaxResult getProductImageByProId(@PathVariable("proId") Integer proId) {
        return AjaxResult.success(mvoProductService.getImagesByProId(proId));
    }

    @PutMapping("/mvo/product/description/")
    public AjaxResult updateDescription(@RequestBody List<mvoProductDescription> productDescriptions) {
        mvoProductService.updateDescription(productDescriptions);
        return AjaxResult.updateSuccess();
    }

    @PostMapping("/mvo/product/image")
    public AjaxResult uploadBrandImage(@RequestParam("productfile") MultipartFile file, @RequestParam("proId")Integer proId,@RequestParam("typeCd")Integer typeCd,@RequestParam("imgId")Integer imgId) throws IOException
    {

        if (!file.isEmpty())
        {
            String productImageUrl = FileUploadUtils.upload(ShopConfig.getProductImagePath(), file);
            if (mvoProductService.uploadProductImage(proId,imgId,typeCd,productImageUrl)){

                return AjaxResult.updateSuccess(productImageUrl);
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    @DeleteMapping("/mvo/product/image/{imgId}")
    public AjaxResult deleteProductImage(@PathVariable("imgId") Integer imgId) {

        mvoProductService.deleteProductImageById(imgId);
        return AjaxResult.deleteSuccess();

    }

    @PutMapping("mvo/product/{proId}/{status}")
    public AjaxResult updateProductProStatus(@PathVariable("proId")Integer proId,@PathVariable("status")String status){
        mvoProductService.updateProductProStatus(proId,status);
        return AjaxResult.updateSuccess();
    }


}
