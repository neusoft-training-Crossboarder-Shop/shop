package neu.train.project.wallet.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.service.PermissionService;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.validate.SelectGroup;
import neu.train.project.validate.UpdateGroup;
import neu.train.project.wallet.pojo.WtaWalletTransactionAduit;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.*;
import neu.train.project.validate.InsertGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@ss.hasAnyPermi('bvo:wallet:use,mvo:wallet:index')")
    @GetMapping("/wallet/account")
    public AjaxResult hasWallet() {
        return AjaxResult.success(walletService.ifWallet(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId())));
    }


    @ApiOperation(value = "Register 钱包,给MVO,BVO用的", httpMethod = "POST", notes = "必选参数有：账户NameaccountName,账户邮箱email，账户密码password,账户币种currency")
    @PreAuthorize("@ss.hasAnyPermi('bvo:wallet:use,mvo:wallet:index')")
    @Transactional
    @PostMapping("/wallet/account")
    public AjaxResult registerWallet(@Validated({InsertGroup.class}) @RequestBody GetANewWallet getANewWallet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Some not null is null now,too bad");
        }
        int userType;
        if (permissionService.hasRole("MVO")) {
            userType=2;
        } else if (permissionService.hasRole("BVO")) {
            userType=1;
        } else {
            throw new RuntimeException("Who are you at all？");
        }
        walletService.insertWallet(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),userType,getANewWallet);
        walletService.insertFund(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()), getANewWallet.getCurrency());
        return AjaxResult.insertSuccess();
    }

    @ApiOperation(value = "登录,给MVO，BVO用的", notes = "必选参数：Account accountName，密码password")
    @PreAuthorize("@ss.hasAnyPermi('bvo:wallet:use,mvo:wallet:index')")
    @PostMapping("/wallet/account/login")
    public AjaxResult walletLogin(@Validated({SelectGroup.class}) @RequestBody GetLogin getLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AjaxResult.error("Some not null is null now,too bad");
        }
        return AjaxResult.success(walletService.selectWalletLogin(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()), getLogin.getAccountName(), getLogin.getPassword()));
    }

    @ApiOperation(value = "更新钱包，给MVO，BVO用的", httpMethod = "PUT", notes = "必选参数：Account accountName,密码password，邮箱email,注意：币种是不可以改的!!!!!!!")
    @PreAuthorize("@ss.hasAnyPermi('bvo:wallet:use,mvo:wallet:index')")
    @Transactional
    @PutMapping("/wallet/account")
    public AjaxResult updateWallet(@Validated({UpdateGroup.class}) @RequestBody GetANewWallet getANewWallet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AjaxResult.error("Some not null is null now,too bad");
        }
        if (walletService.updateWallet(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getANewWallet)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error("Haven't found your wallet,but how can you get this step?");
        }
    }

    @ApiOperation(value = "返回当前User 钱包和钱包余额，感觉爱谁用谁用", httpMethod = "GET", notes = "参数:压根没有")
    @GetMapping("/wallet/fund")
    public AjaxResult selectWalletAndFund() {
        int buyerId = Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId());
        return AjaxResult.success(new SendAWallet(walletService.selectWalletById(buyerId), walletService.selectFundById(buyerId)));
    }


    @ApiOperation(value = "返回钱包流水表Data ，感觉爱谁用谁用", httpMethod = "GET", notes = "可选参数：流水Id,银行卡号bankCardId,流水Statusstatus1申请2complete3Fail，业务Type transactionType1充值2提现3消费4退款,起timebeginTime,止timeendTime")
    @GetMapping("/wallet/transaction")
    public TableDataInfo selectTransaction(GetATransactionQuery getATransactionQuery) {
        getATransactionQuery.setBuyerId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        startPage();
        List<WtrWalletTransactionRecord> wtrWalletTransactionRecords = walletService.selectTransaction(getATransactionQuery);
        return getDataTable(wtrWalletTransactionRecords);
    }

    @ApiOperation(value = "返回流水审计表Data ，给管理员用的", httpMethod = "GET", notes = "可选参数：User Id buyerId，流水Id transactionId，OperationType operationType1申请2提现3消费4退款,起timebeginTime,止timeendTime")
    @PreAuthorize("@ss.hasPermi('system:audit:list')")
    @GetMapping("system/audit/list")
    public TableDataInfo selectAudit(GetAnAuditQuery getAnAuditQuery) {
        startPage();
        List<WtaWalletTransactionAduit> wtaWalletTransactionAduits = walletService.selectAudit(getAnAuditQuery);
        return getDataTable(wtaWalletTransactionAduits);
    }


    @ApiOperation(value = "审计通过,给管理员用的", httpMethod = "PUT", notes = "必选参数：一个列表，每一项包含：流水审计Id transactionAuditId")
    @PreAuthorize("@ss.hasPermi('system:audit:list')")
    @Transactional
    @PutMapping("system/audit/accept/{ids}")
    public AjaxResult acceptAudit(@PathVariable Integer[] ids) {
        walletService.doAudits(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()), ids);
        return AjaxResult.success();
    }


    @ApiOperation(value = "审计拒绝,给管理员用的", httpMethod = "PUT", notes = "必选参数：一个列表，每一项包含：流水审计Id transactionAuditId")
    @PreAuthorize("@ss.hasPermi('system:audit:list')")
    @Transactional
    @PutMapping("system/audit/refuse/{ids}")
    public AjaxResult refuseAudit(@PathVariable Integer[] ids) {
        walletService.rejectAudits(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()), ids);
        return AjaxResult.success();
    }

    @ApiOperation(value = "充值，给MVO,BVO用的", httpMethod = "POST", notes = "必选参数：Operation钱数operateMoney 和 银行卡号bankCardId")
    @PreAuthorize("@ss.hasAnyPermi('bvo:wallet:use,mvo:wallet:index')")
    @Transactional
    @PostMapping("/wallet/transaction/deposit")
    public AjaxResult deposit(@RequestBody MakeATransaction makeATransaction) {
        walletService.tryDeposit(SecurityUtils.getLoginUser().getUser().getUserId().intValue(), makeATransaction);
        return AjaxResult.success();
    }


    @ApiOperation(value = "提现，给MVO,BVO用的", httpMethod = "POST", notes = "必选参数：Operation钱数operateMoney 和 银行卡号bankCardId")
    @PreAuthorize("@ss.hasAnyPermi('bvo:wallet:use,mvo:wallet:index')")
    @Transactional
    @PostMapping("/wallet/transaction/withdraw")
    public AjaxResult withdraw(@RequestBody MakeATransaction makeATransaction) {
        walletService.tryWithdraw(SecurityUtils.getLoginUser().getUser().getUserId().intValue(), makeATransaction);
        return AjaxResult.success();
    }
}
