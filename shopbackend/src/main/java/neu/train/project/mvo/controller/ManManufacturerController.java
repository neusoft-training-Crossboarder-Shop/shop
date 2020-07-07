package neu.train.project.mvo.controller;

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

@RestController
@RequestMapping("/company")
@Validated
public class ManManufacturerController {
    private final Log logger = LogFactory.getLog(ManManufacturerController.class);

    @Autowired
    private ManManufacturerService brandService;

    //    @RequiresPermissions("admin:manufacturer:list")
//    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "brd_id") String order) {
        List<ManManufacturer> brandList = brandService.querySelective(id, name, page, limit, sort, order);
        return ResponseUtil.okList(brandList);
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

    @RequiresPermissions("admin:manufacturer:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody ManManufacturer manufacturer) {
        Object error = validate(manufacturer);
        if (error != null) {
            return error;
        }
        brandService.add(manufacturer);
        return ResponseUtil.ok(manufacturer);
    }

    @RequiresPermissions("admin:manufacturer:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        ManManufacturer manufacturer = brandService.findById(id);
        return ResponseUtil.ok(manufacturer);
    }

    @RequiresPermissions("admin:manufacturer:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody ManManufacturer manufacturer) {
        Object error = validate(manufacturer);
        if (error != null) {
            return error;
        }
        if (brandService.updateById(manufacturer) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(manufacturer);
    }

    @RequiresPermissions("admin:manufacturer:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody ManManufacturer manufacturer) {
        Integer id = manufacturer.getManId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        brandService.deleteById(id);
        return ResponseUtil.ok();
    }

}
