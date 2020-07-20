package neu.train.project.wallet;


import neu.train.project.wallet.mapper.WtaWalletTransactionAduitMapper;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WtaWalletTransactionAduit;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.GetANewWallet;
import neu.train.project.wallet.vo.MakeATransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class test {

    @Autowired
    WalletService walletService;
    @Autowired
    WtaWalletTransactionAduitMapper wtaWalletTransactionAduitMapper;

    @Test
    public void test(){
       WaaWalletAccount waaWalletAccount= new WaaWalletAccount();
        waaWalletAccount.setBuyerId(20);
        waaWalletAccount.setAccountName("getANewWallet.getAccountName()");
        waaWalletAccount.setEmail("email@126.com");
        waaWalletAccount.setPassword("getANewWallet.getPassword()");
        waaWalletAccount.setCreatedBy("20");
        waaWalletAccount.setLastUpdateBy("20");
       //walletService.insertWallet(waaWalletAccount);
    }

    /*
    @Test
    public void test2(){
        GetAnAuditQuery getAnAuditQuery=new GetAnAuditQuery(null,null,null,null,null,null);
        getAnAuditQuery.setTransactionId(3);
        walletService.test(getAnAuditQuery);
    }
    */

    //充值
     @Test
    public void test3(){
         MakeATransaction makeATransaction=new MakeATransaction("5000","2052025");
         walletService.tryDeposit(1,makeATransaction);
         walletService.tryDeposit(1,makeATransaction);
         walletService.tryDeposit(1,makeATransaction);
     }

     //提现
     @Test
     public void test4(){
        MakeATransaction makeATransaction=new MakeATransaction("10000","5555");
        walletService.tryWithdraw(1,makeATransaction);
         walletService.tryWithdraw(1,makeATransaction);
         walletService.tryWithdraw(1,makeATransaction);
     }

     //同意审核1个
     @Test
     public void test7(){
         Integer[] ids={27};
         walletService.doAudits("500",ids);
     }

     //拒绝审核
     @Test
     public void test8(){
         Integer[] ids={30,31};
         walletService.rejectAudits("500",ids);
     }

     @Test
     public void test9(){

     }


     //批量Search
    @Test
    public void test5(){
         Integer[] ids={17};
        List<WtaWalletTransactionAduit> wtaWalletTransactionAduits = wtaWalletTransactionAduitMapper.selectByIds(ids);
        System.out.println(wtaWalletTransactionAduits.size());
        for(WtaWalletTransactionAduit wtaWalletTransactionAduit:wtaWalletTransactionAduits){
            System.out.println("?????????????");
            System.out.println(wtaWalletTransactionAduit.toString());
        }
        System.out.println("!!!!!!!!!");
    }

    //BCryptPasswordEncoder加密
    @Test
    public void test6(){
        GetANewWallet getANewWallet=new GetANewWallet("testA","108","2500921","USD");
         walletService.insertWallet(2,2,getANewWallet);
    }

    @Test
    public void test10(){
        System.out.println(walletService.selectWalletLogin(2,"testA","2500921"));
    }


}
