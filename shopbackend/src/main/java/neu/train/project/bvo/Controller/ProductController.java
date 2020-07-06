package neu.train.project.bvo.Controller;


import io.swagger.annotations.ApiOperation;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.bvo.Service.ProductService;
import neu.train.project.bvo.domain.productDomain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("swagger-resources/bvo")
@RequestMapping("/bvo")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/good/{proId}")
    @ApiOperation("try to get good detail")
    public AjaxResult getProductDetail(@PathVariable("proId") Integer proId){
        Product productDetail = productService.getProductDetail(proId);
        System.out.println(productDetail.getManufacturer());
        System.out.println(productDetail.getProductBrand());
        return AjaxResult.success(productDetail);
    }
}
