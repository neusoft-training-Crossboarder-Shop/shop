package neu.train.project.wallet.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.service.PermissionService;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.validate.SelectGroup;
import neu.train.project.validate.UpdateGroup;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.GetLogin;
import neu.train.project.wallet.vo.SendAWallet;
import neu.train.project.validate.InsertGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import neu.train.project.wallet.vo.GetANewWallet;


@RestController
//@RequestMapping("swagger-resources/wallet")
@RequestMapping("/wallet")
public class WalletController extends BaseController {

    @Autowired
    WalletService walletService;
    @Autowired
    PermissionService permissionService;

    @ApiOperation(value = "返回是否存在钱包,给MVO，BVO用的不用带参数", httpMethod = "GET")
    @GetMapping("/account")
    public AjaxResult hasWallet() {
        return AjaxResult.success(walletService.ifWallet(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId())));
    }


    @ApiOperation(value = "注册钱包", httpMethod = "POST", notes = "必选参数有：账户名称accountName,账户邮箱email，账户密码password,账户币种currency")
    @Transactional
    @PostMapping("/account")
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
    @PostMapping("/account/login")
    public AjaxResult walletLogin(@Validated({SelectGroup.class}) @RequestBody GetLogin getLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AjaxResult.error("必填项有空");
        }
       return AjaxResult.success(walletService.selectWalletLogin(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()),getLogin.getAccountName(),getLogin.getPassword()));
    }

    @ApiOperation(value="更新钱包",httpMethod = "PUT",notes = "必选参数：账号accountName,密码password，邮箱email,注意：币种是不可以改的!!!!!!!")
    @Transactional
    @PutMapping("/account")
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

    @ApiOperation(value = "返回钱包详情,管理员用要给一个具体的用户id来查，其他用户系统会自动get他的buyerId,此时不用带参数", httpMethod = "GET", notes = "可选参数：buyerId")
    @GetMapping("/WalletDetail")
    public AjaxResult getWalletUser(@RequestParam(value = "buyerId", required = false) int buyerId) {
        if (buyerId == 0) {
            buyerId = Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        return AjaxResult.success(new SendAWallet(walletService.selectWalletById(buyerId), walletService.selectFundById(buyerId)));
    }

    @ApiOperation(value = "返回符合条件的钱包简况,给管理员用的,不会显示钱包里有多少钱的,管理员点进去才能详细操作", httpMethod = "GET", notes = "可选查询参数:账号accountName，邮箱email")
    @GetMapping("/walletAdmin")
    public AjaxResult getWalletAdmin(@RequestParam(value = "accountName", required = false) String accountName, @RequestParam(value = "email", required = false) String email) {
        return AjaxResult.success(getDataTable(walletService.selectWalletByNameAndEmail(accountName, email)));
    }


    @ApiOperation(value = "xxx")
    @GetMapping("test")
    public AjaxResult test() {
        System.out.println("####################################");
        System.out.println(walletService.test(2));
        return AjaxResult.success();
    }


    @Transactional
    @RequestMapping("/register")
    public void register(@RequestBody WaaWalletAccount waaWalletAccount) {


    }

}
