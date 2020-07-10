package neu.train.project.mvo.controller;

import neu.train.project.mvo.domain.BrdBrand;
import neu.train.project.mvo.domain.ManManufacturer;
import neu.train.project.mvo.domain.ManManufacturerExample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import neu.train.project.mvo.annotation.*;
import neu.train.project.mvo.util.*;
import neu.train.project.mvo.validator.*;
import neu.train.project.mvo.service.ManManufacturerService;
//import neu.train.project.mvo.annotation.util.ResponseUtil;
//import org.linlinjava.litemall.core.validator.Order;
//import org.linlinjava.litemall.core.validator.Sort;
//import org.linlinjava.litemall.db.domain.ManManufacturer;
//import org.linlinjava.litemall.db.service.ManManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: 高歌
 * @Id: 20175045
 * @Github : EvilicLufas
 */


@RestController
@RequestMapping("/mvo/manufacturer")
@Validated
public class ManManufacturerController {
    private final Log logger = LogFactory.getLog(ManManufacturerController.class);

    @Autowired
    private ManManufacturerService manufacturerService;

//    @RequiresPermissions("mvo:manufacturer:list")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Manufacturer Management"}, button = "Search")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "") String order) {
        List<ManManufacturer> manManufacturerList = manufacturerService.querySelective(id, name, page, limit, sort, order);
//        List<ManManufacturer> manManufacturerList = manufacturerService.query(page, limit);
        return ResponseUtil.okList(manManufacturerList);
    }

    private Object validate(ManManufacturer manufacturer) {
        String name = manufacturer.getNameCn();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

//        String desc = manufacturer.getDesc();
//        if (StringUtils.isEmpty(desc)) {
//            return ResponseUtil.badArgument();
//        }

//        BigDecimal price = manufacturer.getFloorPrice();
//        if (price == null) {
//            return ResponseUtil.badArgument();
//        }
        return null;
    }

//    @RequiresPermissions("mvo:manufacturer:create")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Manufacturer Management"}, button = "Add")
    @PostMapping("/create")
    public Object create(@RequestBody ManManufacturer manufacturer) {
        Object error = validate(manufacturer);
        if (error != null) {
            return error;
        }
        manufacturerService.add(manufacturer);
        return ResponseUtil.ok(manufacturer);
    }

//    @RequiresPermissions("mvo:manufacturer:read")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Manufacturer Management"}, button = "Info")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        ManManufacturer manufacturer = manufacturerService.findById(id);
        return ResponseUtil.ok(manufacturer);
    }

//    @RequiresPermissions("mvo:manufacturer:update")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Manufacturer Management"}, button = "Edit")
    @PostMapping("/update")
    public Object update(@RequestBody ManManufacturer manufacturer) {
        Object error = validate(manufacturer);
        if (error != null) {
            return error;
        }
        if (manufacturerService.updateById(manufacturer) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(manufacturer);
    }

//    @RequiresPermissions("mvo:manufacturer:delete")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Manufacturer Management"}, button = "Delete")
    @PostMapping("/delete")
    public Object delete(@RequestBody ManManufacturer manufacturer) {
        Integer id = manufacturer.getManId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        manufacturerService.deleteById(id);
        return ResponseUtil.ok();
    }

}
