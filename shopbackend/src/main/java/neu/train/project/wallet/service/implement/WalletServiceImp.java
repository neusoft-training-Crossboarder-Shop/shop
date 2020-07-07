package neu.train.project.wallet.service.implement;

import neu.train.common.utils.SecurityUtils;
import neu.train.project.wallet.mapper.WaaWalletAccountMapper;
import neu.train.project.wallet.mapper.WafWalletAccountFundMapper;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WaaWalletAccountExample;
import neu.train.project.wallet.pojo.WafWalletAccountFund;
import neu.train.project.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImp implements WalletService {
    @Autowired
    WaaWalletAccountMapper waaWalletAccountMapper;
    @Autowired
    WafWalletAccountFundMapper wafWalletAccountFundMapper;
    @Autowired
    RedisTemplate redisTemplate;



    @Override
    @Cacheable(value = "walletById",key="#buyerId")
    public WaaWalletAccount selectWalletById(int buyerId){
        return waaWalletAccountMapper.selectByPrimaryKey(buyerId);
    }

    @Cacheable(value = "walletFundById",key="#buyerId")
    @Override
    public WafWalletAccountFund selectFundById(int buyerId){
          return wafWalletAccountFundMapper.selectByPrimaryKey(buyerId);
    }


    @Override
    @Cacheable(value = "walletByNameAndEmail",key="#accountName+#email")
    public List<WaaWalletAccount> selectWalletByNameAndEmail(String accountName,String email) {
        WaaWalletAccountExample waaWalletAccountExample = new WaaWalletAccountExample();
        WaaWalletAccountExample.Criteria waaWalletAccountExampleCriteria = waaWalletAccountExample.createCriteria();
        waaWalletAccountExampleCriteria.andAccountNameEqualTo(accountName);
        waaWalletAccountExampleCriteria.andEmailEqualTo(email);
        List<WaaWalletAccount> walletAccounts = waaWalletAccountMapper.selectByExample(waaWalletAccountExample);
        return walletAccounts;
                }


    @Override
    public WaaWalletAccount insertWallet(WaaWalletAccount waaWalletAccount) {
        waaWalletAccount.setBuyerId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
        waaWalletAccount.setIsActive("Y");
        waaWalletAccount.setStatus((byte)7);
        waaWalletAccount.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        waaWalletAccount.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        waaWalletAccount.setHoldOrderTime("0");
        waaWalletAccount.setAutoPayStatus("0");
        waaWalletAccountMapper.insert(waaWalletAccount);
        return waaWalletAccount;
    }

    @Override
    public WafWalletAccountFund insertFund(String currency){
         WafWalletAccountFund wafWalletAccountFund=new WafWalletAccountFund();
         wafWalletAccountFund.setBuyerId(Math.toIntExact(SecurityUtils.getLoginUser().getUser().getUserId()));
         wafWalletAccountFund.setAvailableMoney(new BigDecimal(0));
         wafWalletAccountFund.setDepositingMoney(new BigDecimal(0));
         wafWalletAccountFund.setWithdrawingMoney(new BigDecimal(0));
         wafWalletAccountFund.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
         wafWalletAccountFund.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
         wafWalletAccountFund.setCurrency(currency);
         wafWalletAccountFundMapper.insert(wafWalletAccountFund);
         return null;
    }

    @Override
    public int test() {
        return 0;
    }

}
