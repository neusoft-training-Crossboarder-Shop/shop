package neu.train.project.wallet.controller;

import io.swagger.annotations.ApiOperation;
import neu.train.common.utils.SecurityUtils;
import neu.train.framework.security.service.PermissionService;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.SendAWallet;
import neu.train.project.validate.InsertGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import neu.train.project.wallet.vo.GetANewWallet;


@RestController
@RequestMapping("swagger-resources/wallet")
public class WalletController extends BaseController {

    @Autowired
    WalletService walletService;
    @Autowired
    PermissionService permissionService;


    @ApiOperation(value = "返回钱包详情,管理员用要给一个具体的用户id来查，其他用户系统会自动get他的buyerId,此时不用带参数", httpMethod = "GET", notes = "可选参数：buyerId")
    @GetMapping("/getWalletDetail")
    public AjaxResult getWalletUser(@RequestParam(value = "buyerId", required = false) int buyerId) {
        if(buyerId==0){
            buyerId=Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        return AjaxResult.success(new SendAWallet(walletService.selectWalletById(buyerId), walletService.selectFundById(buyerId)));
    }

    @ApiOperation(value = "返回符合条件的钱包简况,给管理员用的,不会显示钱包里有多少钱的,管理员点进去才能详细操作", httpMethod = "GET", notes = "可选查询参数:账号accountName，邮箱email")
    @GetMapping("/getWalletAdmin")
    public AjaxResult getWalletAdmin(@RequestParam(value = "accountName", required = false) String accountName, @RequestParam(value = "email", required = false) String email) {
        return AjaxResult.success(getDataTable(walletService.selectWalletByNameAndEmail(accountName,email)));
    }




    @ApiOperation(value = "注册钱包", httpMethod = "POST", notes = "必选参数有：账户名称accountName,账户邮箱email，账户密码password,账户币种currency")
    @Transactional
    @PostMapping("/registerWallet")
    public AjaxResult registerWallet(@Validated({InsertGroup.class})@RequestBody GetANewWallet getANewWallet , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("insertError");
        }
        WaaWalletAccount waaWalletAccount=new WaaWalletAccount();
        if (permissionService.hasRole("MVO")) {
            waaWalletAccount.setAccountType(2);
        } else if (permissionService.hasRole("BVO")) {
            waaWalletAccount.setAccountType(1);
        } else {
            throw new RuntimeException("who are you?");
        }
        waaWalletAccount.setAccountName(getANewWallet.getAccountName());
        waaWalletAccount.setEmail(getANewWallet.getEmail());
        waaWalletAccount.setPassword(getANewWallet.getPassword());
        walletService.insertWallet(waaWalletAccount);
        walletService.insertFund(getANewWallet.getCurrency());
        return AjaxResult.success(waaWalletAccount);
    }


    @ApiOperation(value = "xxx")
    @GetMapping("testinsertdup")
    public AjaxResult test() {
        System.out.println("####################################");
        System.out.println(walletService.test());
        return AjaxResult.success();
    }


    @Transactional
    @RequestMapping("/register")
    public void register(@RequestBody WaaWalletAccount waaWalletAccount) {


    }

}
