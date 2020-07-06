package neu.train.project.mvo.brand.controller;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.mvo.brand.pojo.ManManufacturer;
import neu.train.project.mvo.brand.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/list")
    public AjaxResult listCompany(){
        startPage();
        List<ManManufacturer> manManufacturers=companyService.listCompany(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        return AjaxResult.success(getDataTable(manManufacturers));
    }

        @PostMapping("/create")
    public AjaxResult createCompany(){
return null;
    }
}
