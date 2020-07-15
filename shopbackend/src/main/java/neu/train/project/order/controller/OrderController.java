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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("swagger-resources")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;


    @ApiOperation(value = "模糊查询原始订单列表，给BVO用的", httpMethod = "GET", notes = "可选参数：模糊订单Id stoId,起始时间startTime，结束时间endTime")
    @PreAuthorize("@ss.hasAnyPermi('sys:order:list,mvo:order:list')")
    @GetMapping("/bvo/order/stoOrder/list")
    public TableDataInfo listStoOrders(GetAStoOrderQueryByTime getAStoOrderQueryByTime) {
        getAStoOrderQueryByTime.setBvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        startPage();
        List<SendASimpleSto> sendASimpleStos=orderService.selectStoStrProByTime(getAStoOrderQueryByTime);
        return getDataTable(sendASimpleStos);
    }

    @ApiOperation(value = "精确查询原始订单一个，给BVO用的", httpMethod = "GET", notes = "必选参数：原始订单Id stoId")
    @PreAuthorize("@ss.hasAnyPermi('sys:order:list,mvo:order:list')")
    @GetMapping("/bvo/order/stoOrder/{stoId}")
    public AjaxResult selectSTOById(@PathVariable("stoId") Integer stoId) {
        return AjaxResult.success(orderService.selectStoStrProById(stoId));
    }

    @ApiOperation(value = "更新原始订单一个，给BVO用的", httpMethod = "PUT", notes = "必选参数：原始订单Id stoId,商品数量qty")
    @PreAuthorize("@ss.hasAnyPermi('sys:order:list,mvo:order:list')")
    @Transactional
    @PutMapping("/bvo/order/stoOrder/qty")
    public AjaxResult updateSTOById(@RequestBody GetUpdateASto getUpdateASto) {
        orderService.updateStoById(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()),getUpdateASto);
        return AjaxResult.updateSuccess();
    }


    @ApiOperation(value = "插入收货地址一个，给BVO用的",httpMethod = "POST",notes = "可选参数：一大堆，真的")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PostMapping("bvo/order/shippingAddress")
    public AjaxResult insertAddress(@RequestBody ShaShippingAddress shaShippingAddress){
        shaShippingAddress.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        shaShippingAddress.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.insertOneAddress(shaShippingAddress);
        return AjaxResult.insertSuccess();
    }

    @ApiOperation(value = "更新收货地址一个，给BVO用的",httpMethod = "PUT",notes = "可选参数：一大堆，依旧")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PutMapping("bvo/order/shippingAddress")
    public AjaxResult updateAddress(@RequestBody ShaShippingAddress shaShippingAddress){
        shaShippingAddress.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.updateOneAddress(shaShippingAddress);
        return AjaxResult.updateSuccess();
    }

    @ApiOperation(value = "查询收货地址一个，给BVO用的",httpMethod = "GET",notes = "必选参数：原始订单Id stoId")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @GetMapping("bvo/order/shippingAddress/{stoId}")
    public AjaxResult selectAddress(@PathVariable("stoId") int stoId){
        return AjaxResult.success(orderService.selectAddressByStoId(stoId));
    }

    @ApiOperation(value = "付款，给BVO用的",httpMethod = "POST",notes = "必选参数：原始订单Id stoId,密码password，运费freightCost")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PostMapping("bvo/order/stoOrder/pay")
    public AjaxResult paySTO(@RequestBody GetAPayMessage getAPayMessage) {
        orderService.pay(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getAPayMessage);
    return AjaxResult.success();
    }


    @ApiOperation(value="拿到销售订单商店产品的信息，需要注意的是，商品返回的是现价，不是买入价格，买入价格记录在sao中的purchasePrice中，给BVO用的",httpMethod = "GET",notes = "必选参数：原始订单Id stoId")
    @PreAuthorize("@ss.hasAnyPermi('sys:order:list,mvo:order:list')")
    @GetMapping("bvo/order/salOrder/")
    public AjaxResult getSaoByStoId(@RequestParam("stoId") int stoId){
        return AjaxResult.success(orderService.selectSaoStrProByStoId(stoId));
    }

    @ApiOperation(value = "模糊查询SAOPROSTR，给MVO用的",httpMethod = "GET",notes = "可选参数：模糊订单Id salId,起始时间startTime，结束时间endTime")
    @PreAuthorize("@ss.hasAnyPermi('sys:order:list,mvo:order:list')")
    @GetMapping("mvo/order/salOrder/list")
    public TableDataInfo listSao(GetASaoQueryByTime getASaoQueryByTime){
            getASaoQueryByTime.setMvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
            startPage();
            List<SendComplexSao> sendComplexSaos=orderService.selectSaoStrProByTime(getASaoQueryByTime);
            return getDataTable(sendComplexSaos);
    }

    @ApiOperation(value="更新原始订单状态，只有原始订单有状态",httpMethod = "PUT",notes = "必选参数：原始订单Id stoId,将要变成的状态status")
    @PreAuthorize("@ss.hasPermi('mvo:order:list')")
    @Transactional
    @PutMapping("mvo/order/stoOrder/status")
    public AjaxResult updateStoStatus(@RequestBody GetStoStatusQuery getStoStatusQuery){
        getStoStatusQuery.setMvoId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        orderService.updateStoStatus(getStoStatusQuery);
        return AjaxResult.updateSuccess();
    }

    @ApiOperation(value="多商店下订单，生成一堆sto，给BVO用的",httpMethod = "POST",notes = "必选参数：amount:订购数量，proId:产品ID,storeIds[]:商店Id们")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PostMapping("bvo/browse/purchase")
    public AjaxResult makeStos(@RequestBody  GetPurchaseMessage getPurchaseMessage){
        orderService.insertStos(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getPurchaseMessage);
        return AjaxResult.success();
    }

    @ApiOperation(value = "确认收货，给BVO用的",httpMethod = "PUT",notes = "必选参数：stoId")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PutMapping("bvo/order/accept/{stoId}")
    public AjaxResult acceptProduct(@PathVariable Integer stoId){
        orderService.acceptSto(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),stoId);
        orderService.insertDropItem(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),stoId);
        return AjaxResult.success();
    }

    @ApiOperation(value = "上下架，给BVO用的",httpMethod = "PUT",notes = "必选参数：dilId:到手的商品Id,status:要改到的状态")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PutMapping("store/storeDetail/{dilId}/{status}")
    public AjaxResult onOffShelf(@PathVariable Integer dilId,@PathVariable String status){
            orderService.onOffShelf(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),dilId,status);
            return AjaxResult.success();
    }

    @ApiOperation(value = "修改上架的物品价格和数量",httpMethod = "PUT",notes = "可选参数：dilId:到手的商品Id，salPrice：上架价格，shelfStockAmount")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @Transactional
    @PutMapping("store/storeDetail")
    public AjaxResult updateShelfPriceAmount(GetShelfPriceAmount getShelfPriceAmount){
        orderService.updateShelfPriceAmount(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getShelfPriceAmount);
        return AjaxResult.success();
    }

    @ApiOperation(value = "搜索到手的货",httpMethod = "GET",notes = "可选参数：货Id proId,起始截止时间startTime endTime")
    @PreAuthorize("@ss.hasPermi('sys:order:list')")
    @GetMapping("store/storeDetail/list")
    public TableDataInfo selectSTIs(GetStiQuery getStiQuery){
        startPage();
        List<SendSti> sendStis=orderService.selectSTIs(getStiQuery);
        return getDataTable(sendStis);
    }





}
