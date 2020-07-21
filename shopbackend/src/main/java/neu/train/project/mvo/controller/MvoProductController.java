package neu.train.project.mvo.controller;


import neu.train.common.utils.DateUtils;
import neu.train.common.utils.file.FileUploadUtils;
import neu.train.framework.config.ShopConfig;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.mvo.domain.mvoProduct;
import neu.train.project.mvo.domain.mvoProductDescription;
import neu.train.project.mvo.domain.vo.MvoSearchProduct;
import neu.train.project.mvo.service.IMvoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
//@RequestMapping("swagger-resources/mvo/product")
//@RequestMapping("")
public class MvoProductController extends BaseController {

    @Autowired
    IMvoProductService mvoProductService;

    /**
     * 将前台传递过来的Time   格式的Code串，自动转化为DateType
     */

    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder

    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换Time
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义Time   编辑器
    }

    @GetMapping("/mvo/product/list")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:query')")
    public AjaxResult getProductList(MvoSearchProduct mvoSearchProduct) {
        startPage();
        List<mvoProduct> productList = mvoProductService.getProductList(mvoSearchProduct);
        return AjaxResult.success(getDataTable(productList));
    }

    @PostMapping("/mvo/product")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:add')")
    @Transactional
    public AjaxResult insertProduct(@RequestBody mvoProduct product) {
        mvoProductService.insertProduct(product);
        return AjaxResult.insertSuccess();
    }

    @PutMapping("/mvo/product")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:edit')")
    @Transactional
    public AjaxResult updateProduct(@RequestBody mvoProduct product) {
        mvoProductService.updateProduct(product);
        return AjaxResult.updateSuccess();
    }

    @GetMapping("/mvo/product/{proId}")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:query')")
    public AjaxResult getProductById(@PathVariable("proId")Integer proId ){
        return AjaxResult.success(mvoProductService.getProductByProId(proId));
    }

    @DeleteMapping("/mvo/product/{ids}")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:remove')")
    @Transactional
    public AjaxResult deleteProductById(@PathVariable("ids")int[] id) {
        mvoProductService.deleteProductByIds(id);
        return AjaxResult.deleteSuccess();
    }

    @GetMapping("/mvo/product/description/{proId}")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:query')")
    public AjaxResult getDescriptionByProId(@PathVariable("proId")Integer proId) {
        return AjaxResult.success(mvoProductService.getDescriptionByProId(proId));
    }

    @GetMapping("/mvo/product/image/{proId}")
    public AjaxResult getProductImageByProId(@PathVariable("proId") Integer proId) {
        return AjaxResult.success(mvoProductService.getImagesByProId(proId));
    }

    @PutMapping("/mvo/product/description/")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:edit')")
    @Transactional
    public AjaxResult updateDescription(@RequestBody List<mvoProductDescription> productDescriptions) {
        mvoProductService.updateDescription(productDescriptions);
        return AjaxResult.updateSuccess();
    }

    @PostMapping("/mvo/product/image")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:image:edit')")
    public AjaxResult uploadBrandImage(@RequestParam("productfile") MultipartFile file, @RequestParam("proId")Integer proId,@RequestParam("typeCd")Integer typeCd,@RequestParam("imgId")Integer imgId) throws IOException
    {

        if (!file.isEmpty())
        {
            String productImageUrl = FileUploadUtils.upload(ShopConfig.getProductImagePath(), file);
            if (mvoProductService.uploadProductImage(proId,imgId,typeCd,productImageUrl)){

                return AjaxResult.updateSuccess(productImageUrl);
            }
        }
        return AjaxResult.error("Update Error");
    }

//    @DeleteMapping("/mvo/product/image/{imgId}")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:image:edit')")
    public AjaxResult deleteProductImage(@PathVariable("imgId") Integer imgId) {

        mvoProductService.deleteProductImageById(imgId);
        return AjaxResult.deleteSuccess();

    }

    @PutMapping("mvo/product/{proId}/{status}")
//    @PreAuthorize("@ss.hasPermi('mvo:pro:state:edit')")
    public AjaxResult updateProductProStatus(@PathVariable("proId")Integer proId,@PathVariable("status")String status){
        mvoProductService.updateProductProStatus(proId,status);
        return AjaxResult.updateSuccess();
    }


}
