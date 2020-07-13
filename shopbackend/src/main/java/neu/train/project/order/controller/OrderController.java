package neu.train.project.order.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.order.pojo.ShaShippingAddress;
import neu.train.project.order.service.OrderService;
import neu.train.project.order.vo.GetAStoOrderQueryByTime;
import neu.train.project.order.vo.GetUpdateASto;
import neu.train.project.order.vo.SendASimpleSto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("swagger-resources")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "查询原始订单列表，给BVO用的", httpMethod = "GET", notes = "可选参数：起始时间startTime，结束时间endTime")
    @GetMapping("/bvo/order/stoOder/list")
    public TableDataInfo listStoOrders(GetAStoOrderQueryByTime getAStoOrderQueryByTime) {
        getAStoOrderQueryByTime.setBvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        startPage();
        List<SendASimpleSto> sendASimpleStos=orderService.selectStoStrProByTime(getAStoOrderQueryByTime);
        return getDataTable(sendASimpleStos);
    }

    @ApiOperation(value = "查询原始订单一个，给BVO用的", httpMethod = "GET", notes = "必选参数：原始订单Id stoId")
    @GetMapping("/bvo/order/stoOder")
    public AjaxResult selectSTOById(@RequestParam("stoId") Integer stoId) {
        return AjaxResult.success(orderService.selectStoStrProById(stoId));
    }

    @ApiOperation(value = "更新原始订单一个，给BVO用的", httpMethod = "PUT", notes = "必选参数：原始订单Id stoId,商品数量qty")
    @Transactional
    @PutMapping("/bvo/order/stoOder/qty")
    public AjaxResult updateSTOById(GetUpdateASto getUpdateASto) {
        orderService.updateStoById(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()),getUpdateASto);
        return AjaxResult.success();
    }


    @ApiOperation(value = "插入收货地址一个，给BVO用的",httpMethod = "POST",notes = "可选参数：一大堆，真的")
    @Transactional
    @PostMapping("bvo/order/shippingAddress")
    public AjaxResult insertAddress(@RequestBody ShaShippingAddress shaShippingAddress){
        shaShippingAddress.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        shaShippingAddress.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.insertOneAddress(shaShippingAddress);
        return AjaxResult.success();
    }

    @ApiOperation(value = "更新收货地址一个，给BVO用的",httpMethod = "PUT",notes = "可选参数：一大堆，依旧")
    @Transactional
    @PutMapping("bvo/order/shippingAddress")
    public AjaxResult updateAddress(@RequestBody ShaShippingAddress shaShippingAddress){
        shaShippingAddress.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.updateOneAddress(shaShippingAddress);
        return AjaxResult.success();
    }

    @ApiOperation(value = "查询收获地址一个，给BVO用的",httpMethod = "GET",notes = "必选参数：原始订单Id stoId")
    @GetMapping("bvo/order/shippingAddress")
    public AjaxResult selectAddress(@RequestParam("stoId") int stoId){
        return AjaxResult.success(orderService.selectAddressByStoId(stoId));
    }



}
