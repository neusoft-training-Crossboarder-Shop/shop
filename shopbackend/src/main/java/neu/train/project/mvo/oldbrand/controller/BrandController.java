package neu.train.project.mvo.oldbrand.controller;

import com.github.pagehelper.PageHelper;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.mvo.oldbrand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {
    @Autowired
    BrandService brandService;



    @GetMapping("/list")
    public AjaxResult getBrand (@RequestParam(value = "id",required = false) int id,@RequestParam(value="name",required = false)String name,@RequestParam(value="page")int page,@RequestParam(value="limit")int limit){
        PageHelper.startPage(page, limit);
        startPage();

        System.out.println();
       return null;
    }

    @PostMapping("/create")
    public AjaxResult createBrand(@RequestBody HashMap<String,Object> data){

            return null;


    }


    /*Required request body is missing: public neu.train.framework.web.domain.AjaxResult neu.train.project.mvo.oldbrand.controller.BrandController.getBrand(java.util.HashMap<java.lang.String, java.lang.Object>)
    * */


}
