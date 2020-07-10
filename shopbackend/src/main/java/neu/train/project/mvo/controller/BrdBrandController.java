package neu.train.project.mvo.controller;

import neu.train.project.mvo.domain.BrdBrand;
import neu.train.project.mvo.service.BrdBrandService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import neu.train.project.mvo.annotation.*;
import neu.train.project.mvo.util.*;
import neu.train.project.mvo.validator.*;
import neu.train.project.mvo.domain.BrdBrand;
import neu.train.project.mvo.service.BrdBrandService;
//import neu.train.project.mvo.annotation.util.ResponseUtil;
//import org.linlinjava.litemall.core.validator.Order;
//import org.linlinjava.litemall.core.validator.Sort;
//import org.linlinjava.litemall.db.domain.BrdBrand;
//import org.linlinjava.litemall.db.service.BrdBrandService;
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
@RequestMapping("/mvo/brand")
@Validated
public class BrdBrandController {
    private final Log logger = LogFactory.getLog(BrdBrandController.class);

    @Autowired
    private BrdBrandService brandService;

//    @RequiresPermissions("mvo:brand:list")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Search")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "") String order) {
        List<BrdBrand> brandList = brandService.querySelective(id, name, page, limit, sort, order);
//        List<BrdBrand> brandList = brandService.query(page, limit);
        return ResponseUtil.okList(brandList);
    }

    private Object validate(BrdBrand brand) {
        String name = brand.getNameCn();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

//        String desc = brand.getDesc();
//        if (StringUtils.isEmpty(desc)) {
//            return ResponseUtil.badArgument();
//        }

//        BigDecimal price = brand.getFloorPrice();
//        if (price == null) {
//            return ResponseUtil.badArgument();
//        }
        return null;
    }

//    @RequiresPermissions("mvo:brand:create")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Add")
    @PostMapping("/create")
    public Object create(@RequestBody BrdBrand brand) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        brandService.add(brand);
        return ResponseUtil.ok(brand);
    }

//    @RequiresPermissions("mvo:brand:read")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Info")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        BrdBrand brand = brandService.findById(id);
        return ResponseUtil.ok(brand);
    }

//    @RequiresPermissions("mvo:brand:update")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Edit")
    @PostMapping("/update")
    public Object update(@RequestBody BrdBrand brand) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        if (brandService.updateById(brand) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(brand);
    }

//    @RequiresPermissions("mvo:brand:delete")
//    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Delete")
    @PostMapping("/delete")
    public Object delete(@RequestBody BrdBrand brand) {
        Integer id = brand.getBrdId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        brandService.deleteById(id);
        return ResponseUtil.ok();
    }

}
