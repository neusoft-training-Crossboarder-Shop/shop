package neu.train.project.wallet.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.service.PermissionService;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.validate.SelectGroup;
import neu.train.project.validate.UpdateGroup;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WtaWalletTransactionAduit;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.*;
import neu.train.project.validate.InsertGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("swagger-resources")
public class WalletController extends BaseController {

    @Autowired
    WalletService walletService;
    @Autowired
    PermissionService permissionService;

    @ApiOperation(value = "返回是否存在钱包,给MVO，BVO用的不用带参数", httpMethod = "GET")
    @GetMapping("/wallet/account")
    public AjaxResult hasWallet() {
        return AjaxResult.success(walletService.ifWallet(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId())));
    }

    @ApiOperation(value = "注册钱包", httpMethod = "POST", notes = "必选参数有：账户名称accountName,账户邮箱email，账户密码password,账户币种currency")
    @Transactional
    @PostMapping("/wallet/account")
    public AjaxResult registerWallet(@Validated({InsertGroup.class}) @RequestBody GetANewWallet getANewWallet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("必填项有空");
        }
        WaaWalletAccount waaWalletAccount = new WaaWalletAccount();
        if (permissionService.hasRole("MVO")) {
            waaWalletAccount.setAccountType(2);
        } else if (permissionService.hasRole("BVO")) {
            waaWalletAccount.setAccountType(1);
        } else {
            throw new RuntimeException("你究竟是什么人？");
    }
        waaWalletAccount.setBuyerId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        waaWalletAccount.setAccountName(getANewWallet.getAccountName());
        waaWalletAccount.setEmail(getANewWallet.getEmail());
        waaWalletAccount.setPassword(getANewWallet.getPassword());
        waaWalletAccount.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        waaWalletAccount.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        walletService.insertWallet(waaWalletAccount);
        walletService.insertFund(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()), getANewWallet.getCurrency());
        return AjaxResult.insertSuccess();
    }

    @ApiOperation(value = "登录",notes = "必选参数：账号accountName，密码password")
    @PostMapping("/wallet/account/login")
    public AjaxResult walletLogin(@Validated({SelectGroup.class}) @RequestBody GetLogin getLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AjaxResult.error("必填项有空");
        }
       return AjaxResult.success(walletService.selectWalletLogin(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getLogin.getAccountName(),getLogin.getPassword()));
    }

    @ApiOperation(value="更新钱包",httpMethod = "PUT",notes = "必选参数：账号accountName,密码password，邮箱email,注意：币种是不可以改的!!!!!!!")
    @Transactional
    @PutMapping("/wallet/account")
    public AjaxResult updateWallet(@Validated({UpdateGroup.class}) @RequestBody GetANewWallet getANewWallet, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return AjaxResult.error("必填项有空");
        }
       WaaWalletAccount waaWalletAccount=new WaaWalletAccount();
        waaWalletAccount.setBuyerId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        waaWalletAccount.setAccountName(getANewWallet.getAccountName());
        waaWalletAccount.setPassword(getANewWallet.getPassword());
        waaWalletAccount.setEmail(getANewWallet.getEmail());
        if(walletService.updateWallet(waaWalletAccount)){
            return AjaxResult.success();
        }else{
            return AjaxResult.error("没找到你的账户，你是怎么做到这一步的？");
        }
    }

    @ApiOperation(value="返回当前用户钱包和钱包余额，给用户用的",httpMethod = "GET",notes = "参数:压根没有")
     @GetMapping("/wallet/fund")
    public AjaxResult selectWalletAndFund(){
        int buyerId = Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId());
        return AjaxResult.success(new SendAWallet(walletService.selectWalletById(buyerId), walletService.selectFundById(buyerId)));
    }


@ApiOperation(value="返回钱包流水表数据，给用户用的",httpMethod = "GET",notes = "可选参数：流水Id,银行卡号bankCardId,流水状态status1申请2完成3失败，业务类型transactionType1充值2提现3消费4退款,起时间beginTime,止时间endTime")
@GetMapping("/wallet/transaction")
public TableDataInfo selectTransaction(GetATransactionQuery getATransactionQuery){
    getATransactionQuery.setBuyerId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
    startPage();
    List<WtrWalletTransactionRecord> wtrWalletTransactionRecords=walletService.selectTransaction(getATransactionQuery);
    return getDataTable(wtrWalletTransactionRecords);
}

@ApiOperation(value="返回流水审计表数据，给管理员用的",httpMethod = "GET",notes ="可选参数：用户Id buyerId，流水Id transactionId，操作类型operationType1申请2提现3消费4退款,起时间beginTime,止时间endTime")
@GetMapping("system/audit/list")
public TableDataInfo selectAudit(GetAnAuditQuery getAnAuditQuery){
    startPage();
    List<WtaWalletTransactionAduit> wtaWalletTransactionAduits=walletService.selectAudit(getAnAuditQuery);
    return getDataTable(wtaWalletTransactionAduits);
}

@ApiOperation(value="审计通过,给管理员用的",httpMethod = "PUT",notes = "必选参数：一个列表，每一项包含：流水审计Id transactionAuditId")
@Transactional
@PutMapping("system/audit/accept/{ids}")
public AjaxResult acceptAudit(@PathVariable Integer[] ids){
        walletService.doAudits(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()),ids);
        return AjaxResult.success();
}


@ApiOperation(value="审计拒绝,给管理员用的",httpMethod = "PUT",notes = "必选参数：一个列表，每一项包含：流水审计Id transactionAuditId")
@Transactional
@PutMapping("system/audit/refuse/{ids}")
public AjaxResult refuseAudit(@PathVariable Integer[] ids){
    walletService.doAudits(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()),ids);
    return AjaxResult.success();
}

@ApiOperation(value="充值")
@RequestMapping("/idontknow")
public AjaxResult emmm(){
return null;
}


    @ApiOperation(value="提现")
    @RequestMapping("/idontknow")
    public AjaxResult emmmm(){
        return null;
    }




    @ApiOperation(value = "xxx")
    @GetMapping("test")
    public AjaxResult test() {
        System.out.println("####################################");
        System.out.println(walletService.test(2));
        return AjaxResult.success();
    }



}
