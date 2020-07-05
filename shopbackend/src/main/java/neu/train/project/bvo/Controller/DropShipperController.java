package neu.train.project.bvo.Controller;

import io.swagger.annotations.ApiOperation;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.bvo.Service.DropShipperService;
import neu.train.project.bvo.domain.DropShipper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("swagger-resources/bvo/")
@RequestMapping("/bvo")
public class DropShipperController extends BaseController {
    @Autowired
    DropShipperService dropShipperService;

    @GetMapping("/profile")
    @ApiOperation("try to fetch basic info of dropshipper")
    public AjaxResult getBasicDropShipperInfo() {
        return dropShipperService.getBasicInfo();
    };

    @PostMapping("/profile")
    @ApiOperation("try to register dropShipper")
    public AjaxResult add(@RequestBody DropShipper dropShipper){
        return dropShipperService.register(dropShipper);
    }

    @PutMapping("/profile")
    @ApiOperation("try to update dropShipper")
    public AjaxResult update(@RequestBody DropShipper dropShipper){

        return dropShipperService.update(dropShipper);

    }
}
