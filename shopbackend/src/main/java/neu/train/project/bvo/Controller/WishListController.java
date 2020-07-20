package neu.train.project.bvo.Controller;


import io.swagger.annotations.ApiOperation;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.bvo.Service.ProductService;
import neu.train.project.bvo.domain.SimpleProduct;
import neu.train.project.bvo.domain.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
//@RequestMapping("/swagger-resources/bvo/wishlist")
@RequestMapping("/bvo/wishlist")
public class WishListController extends BaseController {
    @Autowired
    ProductService productService;
    @GetMapping("/list")
    @ApiOperation("get withslist of drp_ID ")
    public AjaxResult getWishList() {
//        startPage();
        List<SimpleProduct> wishLists = productService.getWishListByDsrId();
//        TableDataInfo dataTable = getDataTable(wishLists);
        return AjaxResult.success(wishLists);
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteByProId(@PathVariable("id")Integer id){
        int result=productService.deleteProInWishlistByProId(id);
        if (result == 1) {
            return AjaxResult.deleteSuccess();
        }else{
            return AjaxResult.error(" Delete Fail");
        }
    }

    @PostMapping("/")
    public AjaxResult addProInWishList(@RequestBody WishList wishList) {

        int result = productService.addProInWishListByProId(wishList);
        if (result == 1) {
            return AjaxResult.insertSuccess();
        }else if (result == -1){
            return AjaxResult.error("Already in wishlist");
        }else{
            return AjaxResult.error("Something is wrong");
        }
    }



}
