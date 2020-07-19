//package neu.train.project.mvo.controller;
//
//import neu.train.project.mvo.domain.PrcProductCategory;
//import neu.train.project.mvo.service.BrdBrandService;
//import neu.train.project.mvo.util.ResponseUtil;
//import neu.train.project.mvo.validator.Order;
//import neu.train.project.mvo.validator.Sort;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//
///**
// * @Author: 高歌
// * @Id: 20175045
// * @Github : EvilicLufas
// */
//
//
//@RestController
//@RequestMapping("/mvo/GoodsCategory")
//@Validated
//public class PrcProductCategoryController {
//    private final Log logger = LogFactory.getLog(neu.train.project.mvo.controller.BrdBrandController.class);
//
//    @Autowired
//    private BrdBrandService brandService;
//
//    //    @RequiresPermissions("mvo:prcProductCategory:list")
////    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Search")
//    @GetMapping("/list")
//    public Object list(String id, String name,
//                       @RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer limit,
//                       @Sort @RequestParam(defaultValue = "create_time") String sort,
//                       @Order @RequestParam(defaultValue = "") String order) {
//        List<PrcProductCategory> brandList = brandService.querySelective(id, name, page, limit, sort, order);
////        List<PrcProductCategory> brandList = brandService.query(page, limit);
//        return ResponseUtil.okList(brandList);
//    }
//
//    private Object validate(PrcProductCategory prcProductCategory) {
//        String name = prcProductCategory.getNameCn();
//        if (StringUtils.isEmpty(name)) {
//            return ResponseUtil.badArgument();
//        }
//
////        String desc = prcProductCategory.getDesc();
////        if (StringUtils.isEmpty(desc)) {
////            return ResponseUtil.badArgument();
////        }
//
////        BigDecimal price = prcProductCategory.getFloorPrice();
////        if (price == null) {
////            return ResponseUtil.badArgument();
////        }
//        return null;
//    }
//
//    //    @RequiresPermissions("mvo:prcProductCategory:create")
////    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Add")
//    @PostMapping("/create")
//    public Object create(@RequestBody PrcProductCategory prcProductCategory) {
//        Object error = validate(prcProductCategory);
//        if (error != null) {
//            return error;
//        }
//        brandService.add(prcProductCategory);
//        return ResponseUtil.ok(prcProductCategory);
//    }
//
//    //    @RequiresPermissions("mvo:prcProductCategory:read")
////    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Info")
//    @GetMapping("/read")
//    public Object read(@NotNull Integer id) {
//        PrcProductCategory prcProductCategory = brandService.findById(id);
//        return ResponseUtil.ok(prcProductCategory);
//    }
//
//    //    @RequiresPermissions("mvo:prcProductCategory:update")
////    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Edit")
//    @PostMapping("/update")
//    public Object update(@RequestBody PrcProductCategory prcProductCategory) {
//        Object error = validate(prcProductCategory);
//        if (error != null) {
//            return error;
//        }
//        if (brandService.updateById(prcProductCategory) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//        return ResponseUtil.ok(prcProductCategory);
//    }
//
//    //    @RequiresPermissions("mvo:prcProductCategory:delete")
////    @RequiresPermissionsDesc(menu = {"Manufacturer Manager", "Brand Management"}, button = "Delete")
//    @PostMapping("/delete")
//    public Object delete(@RequestBody PrcProductCategory prcProductCategory) {
//        Integer id = prcProductCategory.getBrdId();
//        if (id == null) {
//            return ResponseUtil.badArgument();
//        }
//        brandService.deleteById(id);
//        return ResponseUtil.ok();
//    }
//
//}