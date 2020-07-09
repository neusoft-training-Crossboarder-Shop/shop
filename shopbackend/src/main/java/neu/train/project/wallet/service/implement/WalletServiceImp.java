package neu.train.project.wallet.service.implement;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.project.wallet.mapper.WaaWalletAccountMapper;
import neu.train.project.wallet.mapper.WafWalletAccountFundMapper;
import neu.train.project.wallet.mapper.WtrWalletTransactionRecordMapper;
import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WaaWalletAccountExample;
import neu.train.project.wallet.pojo.WafWalletAccountFund;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.GetATransactionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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
    WtrWalletTransactionRecordMapper wtrWalletTransactionRecordMapper;
    @Autowired
    RedisCache redisCache;
    @Autowired
    RedisTemplate redisTemplate;




    //查询有没有这个用户ID的钱包,缓存"walletById:"+buyerId
    @Override
    public  boolean ifWallet(int buyerId){
        if(redisCache.getCacheObject("walletById:"+buyerId)!=null){
            return true;
        }
        WaaWalletAccount waaWalletAccount=waaWalletAccountMapper.selectByPrimaryKey(buyerId);
        if(waaWalletAccount!=null){
            redisCache.setCacheObject("walletById:"+buyerId,waaWalletAccount);
            return true;
        }else{
            return false;
        }
    }

    //用于登录,缓存"walletById:"+buyerId
    @Override
    public  boolean selectWalletLogin(int buyerId,String accountName,String password){
        WaaWalletAccount waaWalletAccount=redisCache.getCacheObject("walletById:"+buyerId);
        if(waaWalletAccount!=null){
            if(waaWalletAccount.getBuyerId()==buyerId && waaWalletAccount.getAccountName().equals(accountName) && waaWalletAccount.getPassword().equals(password)){
                return true;
            }
        }
        WaaWalletAccountExample waaWalletAccountExample = new WaaWalletAccountExample();
        WaaWalletAccountExample.Criteria waaWalletAccountExampleCriteria = waaWalletAccountExample.createCriteria();
        waaWalletAccountExampleCriteria.andBuyerIdEqualTo(buyerId);
        waaWalletAccountExampleCriteria.andAccountNameEqualTo(accountName);
        waaWalletAccountExampleCriteria.andPasswordEqualTo(password);
        List<WaaWalletAccount> waaWalletAccounts=waaWalletAccountMapper.selectByExample(waaWalletAccountExample);
        if(!waaWalletAccounts.isEmpty()){
            redisCache.setCacheObject("walletById:"+buyerId,waaWalletAccounts.get(0));
            return true;
        }else{
            return false;
        }
    }

    //用于更新,缓存"walletById:"+buyerId
    @Override
    public boolean updateWallet(WaaWalletAccount waaWalletAccount){
        WaaWalletAccountExample waaWalletAccountExample = new WaaWalletAccountExample();
        WaaWalletAccountExample.Criteria waaWalletAccountExampleCriteria = waaWalletAccountExample.createCriteria();
        waaWalletAccountExampleCriteria.andBuyerIdEqualTo(waaWalletAccount.getBuyerId());
        if(waaWalletAccountMapper.updateByExampleSelective(waaWalletAccount,waaWalletAccountExample)==0){
            return false;
        }else{
            redisCache.setCacheObject("walletById:"+waaWalletAccount.getBuyerId(),waaWalletAccount);
            return true;
        }
    }

    //用于注册,缓存"walletById:"+buyerId
    @Override
    public boolean insertWallet(WaaWalletAccount waaWalletAccount) {
        if(ifWallet(waaWalletAccount.getBuyerId())){
           throw new RuntimeException("你是不是已经有钱包了？？？？");
        }
        waaWalletAccount.setIsActive("Y");
        waaWalletAccount.setStatus((byte)7);
        waaWalletAccount.setHoldOrderTime("0");
        waaWalletAccount.setAutoPayStatus("0");
        redisTemplate.opsForValue().set("walletById:"+waaWalletAccount.getBuyerId(),waaWalletAccount);
        waaWalletAccountMapper.insertSelective(waaWalletAccount);
        return true;
    }

    //注册的时候，插入fund信息，缓存"fundById:"+buyerId
    @Override
    public boolean insertFund(int buyerId,String currency){
        WafWalletAccountFund wafWalletAccountFund=new WafWalletAccountFund();
        wafWalletAccountFund.setBuyerId(buyerId);
        wafWalletAccountFund.setAvailableMoney(new BigDecimal(0));
        wafWalletAccountFund.setDepositingMoney(new BigDecimal(0));
        wafWalletAccountFund.setWithdrawingMoney(new BigDecimal(0));
        wafWalletAccountFund.setCreatedBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        wafWalletAccountFund.setLastUpdateBy(String.valueOf(SecurityUtils.getLoginUser().getUser().getUserId()));
        wafWalletAccountFund.setCurrency(currency);
        redisTemplate.opsForValue().set("fundById:"+buyerId,wafWalletAccountFund);
        wafWalletAccountFundMapper.insertSelective(wafWalletAccountFund);
        return true;
    }

    //根据用户Id，检索钱包，缓存"walletById:"+buyerId
    @Override
    public WaaWalletAccount selectWalletById(int buyerId){
        WaaWalletAccount waaWalletAccount=redisCache.getCacheObject("walletById:"+buyerId);
        if(waaWalletAccount!=null){
            return waaWalletAccount;
        }
        waaWalletAccount=waaWalletAccountMapper.selectByPrimaryKey(buyerId);
        redisCache.setCacheObject("walletById:"+buyerId,waaWalletAccount);
        return waaWalletAccount;
    }

    //根据用户Id，检索钱包余额之类的东西，缓存"fundById:"+buyerId
    @Override
    public WafWalletAccountFund selectFundById(int buyerId){
        WafWalletAccountFund wafWalletAccountFund=redisCache.getCacheObject("fundById"+buyerId);
        if(wafWalletAccountFund!=null){
            return wafWalletAccountFund;
        }
        wafWalletAccountFund=wafWalletAccountFundMapper.selectByPrimaryKey(buyerId);
        redisCache.setCacheObject("fundById"+buyerId,wafWalletAccountFund);
        return wafWalletAccountFund;
    }

    //多条件查询钱包流水，不缓存
    @Override
    public List<WtrWalletTransactionRecord> selectTransaction(GetATransactionQuery getATransactionQuery){
        return wtrWalletTransactionRecordMapper.selectByMany(getATransactionQuery);
    }





    @Override
    public boolean test(int num) {
      redisTemplate.opsForValue().set("shabitest:"+3,1);
      if(redisTemplate.opsForValue().get("shabitest:"+3).equals(1)){
          return true;
      }
      return false;
    }

}
