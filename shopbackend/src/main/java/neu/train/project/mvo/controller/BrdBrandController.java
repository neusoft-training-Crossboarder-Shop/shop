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

@RestController
@RequestMapping("/brand")
@Validated
public class BrdBrandController {
    private final Log logger = LogFactory.getLog(BrdBrandController.class);

    @Autowired
    private BrdBrandService brandService;

//    @RequiresPermissions("admin:brand:list")
//    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "create_time") String sort,
                       @Order @RequestParam(defaultValue = "") String order) {
        List<BrdBrand> brandList = brandService.querySelective(id, name, page, limit, sort, order);
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

    @RequiresPermissions("admin:brand:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody BrdBrand brand) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        brandService.add(brand);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:brand:read")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        BrdBrand brand = brandService.findById(id);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:brand:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "编辑")
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

    @RequiresPermissions("admin:brand:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "删除")
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
