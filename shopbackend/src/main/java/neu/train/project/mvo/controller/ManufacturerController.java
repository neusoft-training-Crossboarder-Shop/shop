package neu.train.project.mvo.controller;

import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.mvo.domain.mvoManufacturer;
import neu.train.project.mvo.service.IManufacturerService;
import neu.train.project.mvo.service.impl.ManufacturerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManufacturerController {

    @Autowired
    IManufacturerService manufacturerService;

    @GetMapping("/mvo/manufacturer")
    public AjaxResult getManufacturer(){
        return AjaxResult.success(manufacturerService.getManufacturer());
    }
//    @PreAuthorize("ss.hasPermi('mvo:profile:add')")
    @PostMapping("/mvo/manufacturer")
    public AjaxResult insertManufacturer(@RequestBody mvoManufacturer manufacturer){
        manufacturerService.insertManufacturer(manufacturer);
        return AjaxResult.insertSuccess();
    }

    @PutMapping("/mvo/manufacturer")
    public AjaxResult updateManufacturer(@RequestBody mvoManufacturer manufacturer){
        manufacturerService.updateManufacturer(manufacturer);
        return AjaxResult.updateSuccess();
    }
}
