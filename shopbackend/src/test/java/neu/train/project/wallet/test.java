package neu.train.project.wallet;


import neu.train.common.utils.SecurityUtils;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.service.WalletService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class test {

    @Autowired
    WalletService walletService;

    @Test
    public void test(){
       WaaWalletAccount waaWalletAccount= new WaaWalletAccount();
        waaWalletAccount.setBuyerId(20);
        waaWalletAccount.setAccountName("getANewWallet.getAccountName()");
        waaWalletAccount.setEmail("email@126.com");
        waaWalletAccount.setPassword("getANewWallet.getPassword()");
        waaWalletAccount.setCreatedBy("20");
        waaWalletAccount.setLastUpdateBy("20");
       walletService.insertWallet(waaWalletAccount);
    }


}
