package neu.train.project.order.controller;


import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.order.pojo.ShaShippingAddress;
import neu.train.project.order.service.OrderService;
import neu.train.project.order.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("swagger-resources")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;


    @ApiOperation(value = "模糊查询原始订单列表，给BVO用的", httpMethod = "GET", notes = "可选参数：模糊订单Id stoId,起始时间startTime，结束时间endTime")
    @GetMapping("/bvo/order/stoOrder/list")
    public TableDataInfo listStoOrders(GetAStoOrderQueryByTime getAStoOrderQueryByTime) {
        getAStoOrderQueryByTime.setBvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        startPage();
        List<SendASimpleSto> sendASimpleStos=orderService.selectStoStrProByTime(getAStoOrderQueryByTime);
        return getDataTable(sendASimpleStos);
    }

    @ApiOperation(value = "精确查询原始订单一个，给BVO用的", httpMethod = "GET", notes = "必选参数：原始订单Id stoId")
    @GetMapping("/bvo/order/stoOrder/{stoId}")
    public AjaxResult selectSTOById(@PathVariable("stoId") Integer stoId) {
        return AjaxResult.success(orderService.selectStoStrProById(stoId));
    }

    @ApiOperation(value = "更新原始订单一个，给BVO用的", httpMethod = "PUT", notes = "必选参数：原始订单Id stoId,商品数量qty")
    @Transactional
    @PutMapping("/bvo/order/stoOrder/qty")
    public AjaxResult updateSTOById(@RequestBody GetUpdateASto getUpdateASto) {
        orderService.updateStoById(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()),getUpdateASto);
        return AjaxResult.updateSuccess();
    }


    @ApiOperation(value = "插入收货地址一个，给BVO用的",httpMethod = "POST",notes = "可选参数：一大堆，真的")
    @Transactional
    @PostMapping("bvo/order/shippingAddress")
    public AjaxResult insertAddress(@RequestBody ShaShippingAddress shaShippingAddress){
        shaShippingAddress.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        shaShippingAddress.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.insertOneAddress(shaShippingAddress);
        return AjaxResult.insertSuccess();
    }

    @ApiOperation(value = "更新收货地址一个，给BVO用的",httpMethod = "PUT",notes = "可选参数：一大堆，依旧")
    @Transactional
    @PutMapping("bvo/order/shippingAddress")
    public AjaxResult updateAddress(@RequestBody ShaShippingAddress shaShippingAddress){
        shaShippingAddress.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.updateOneAddress(shaShippingAddress);
        return AjaxResult.updateSuccess();
    }

    @ApiOperation(value = "查询收获地址一个，给BVO用的",httpMethod = "GET",notes = "必选参数：原始订单Id stoId")
    @GetMapping("bvo/order/shippingAddress/{stoId}")
    public AjaxResult selectAddress(@PathVariable("stoId") int stoId){
        return AjaxResult.success(orderService.selectAddressByStoId(stoId));
    }

    @ApiOperation(value = "付款，给BVO用的",httpMethod = "POST",notes = "必选参数：原始订单Id stoId,密码password，运费freightCost")
    @Transactional
    @PostMapping("bvo/order/stoOrder/pay")
    public AjaxResult paySTO(@RequestBody GetAPayMessage getAPayMessage) {
        orderService.pay(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getAPayMessage);
    return AjaxResult.success();
    }


    @ApiOperation(value="拿到销售订单商店产品的信息，需要注意的是，商品返回的是现价，不是买入价格，买入价格记录在sao中的purchasePrice中，给BVO用的？",httpMethod = "GET",notes = "必选参数：原始订单Id stoId")
    @GetMapping("bvo/order/salOrder/")
    public AjaxResult getSaoByStoId(@RequestParam("stoId") int stoId){
        return AjaxResult.success(orderService.selectSaoStrProByStoId(stoId));
    }

    @ApiOperation(value = "模糊查询SAOPROSTR，给MVO用的",httpMethod = "GET",notes = "可选参数：模糊订单Id salId,起始时间startTime，结束时间endTime")
    @GetMapping("mvo/order/salOrder/list")
    public TableDataInfo listSao(GetASaoQueryByTime getASaoQueryByTime){
            getASaoQueryByTime.setMvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
            startPage();
            List<SendComplexSao> sendComplexSaos=orderService.selectSaoStrProByTime(getASaoQueryByTime);
            return getDataTable(sendComplexSaos);
    }

    @ApiOperation(value="更新原始订单状态，只有原始订单有状态",httpMethod = "PUT",notes = "必选参数：原始订单Id stoId,将要变成的状态status")
    @Transactional
    @PutMapping("mvo/order/stoOrder/status")
    public AjaxResult updateStoStatus(GetStoStatusQuery getStoStatusQuery){
        getStoStatusQuery.setMvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.updateStoStatus(getStoStatusQuery);
        return AjaxResult.updateSuccess();
    }

}
