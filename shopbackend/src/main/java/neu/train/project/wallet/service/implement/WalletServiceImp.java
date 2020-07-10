package neu.train.project.wallet.service.implement;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.project.wallet.mapper.WaaWalletAccountMapper;
import neu.train.project.wallet.mapper.WafWalletAccountFundMapper;
import neu.train.project.wallet.mapper.WtaWalletTransactionAduitMapper;
import neu.train.project.wallet.mapper.WtrWalletTransactionRecordMapper;
import neu.train.project.wallet.pojo.*;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.GetATransactionQuery;
import neu.train.project.wallet.vo.GetAnAuditQuery;
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
    WtaWalletTransactionAduitMapper wtaWalletTransactionAduitMapper;
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

    //多条件查询流水审批，不缓存
    @Override
    public List<WtaWalletTransactionAduit> selectAudit(GetAnAuditQuery getAnAuditQuery){
        return wtaWalletTransactionAduitMapper.selectByMany(getAnAuditQuery);
    }
    //批量同意审核
    @Override
    public  boolean doAudits(String managerId,Integer[] ids){
        List<WtaWalletTransactionAduit> wtaWalletTransactionAduits=wtaWalletTransactionAduitMapper.selectByIds(ids);
        for(WtaWalletTransactionAduit wtaWalletTransactionAduit:wtaWalletTransactionAduits){
            //状态 1 -申请 , 2 -完成 , -3-失败
            if(wtaWalletTransactionAduit.getStatus()==2){
                    doAudit(managerId,wtaWalletTransactionAduit);
            }else if(wtaWalletTransactionAduit.getStatus()==3){
                rejectAudit(managerId,wtaWalletTransactionAduit);
            }else{
                throw new RuntimeException("一定发生了什么不愉快的事");
            }
        }
        return true;
    }


    //同意单个钱包，缓存"fundById:"+buyerId
    @Override
    public boolean doAudit(String managerId,WtaWalletTransactionAduit wtaWalletTransactionAduit){
        WafWalletAccountFund wafWalletAccountFund=selectFundById(wtaWalletTransactionAduit.getBuyerId());
       // 充值前余额，充值中金额，提现中金额
        BigDecimal availableMoney=wafWalletAccountFund.getAvailableMoney();
        BigDecimal depositingMoney=wafWalletAccountFund.getDepositingMoney();
        BigDecimal withdrawingMoney=wafWalletAccountFund.getWithdrawingMoney();
        BigDecimal operateMoney=wtaWalletTransactionAduit.getOperateMoney();
        //设置好充值前余额，充值中金额，提现中金额
        wtaWalletTransactionAduit.setAvailableMoneyBefore(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyBefore(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyBefore(withdrawingMoney);
        //操作类型 1-充值 2-提现 3消费 4-退款
        if(wtaWalletTransactionAduit.getOperateType()==1){
            //充值，余额加，充值中金额减，提现中不变
            availableMoney=availableMoney.add(operateMoney);
            depositingMoney=depositingMoney.subtract(operateMoney);
        }else if(wtaWalletTransactionAduit.getOperateType()==2){
            //提现，余额减，充值中金额不变，提现中减
            availableMoney=availableMoney.subtract(operateMoney);
            withdrawingMoney=withdrawingMoney.subtract(operateMoney);
        }else if(wtaWalletTransactionAduit.getOperateType()==3){
            //消费，余额减，充值中金额不变，提现中不变
            availableMoney=availableMoney.subtract(operateMoney);
        }else if(wtaWalletTransactionAduit.getOperateType()==4){
            //退款，余额加，充值中金额不变，提现中不变
            availableMoney=availableMoney.add(operateMoney);
        }
        wtaWalletTransactionAduit.setAvailableMoneyAfter(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyAfter(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyAfter(withdrawingMoney);
        //通过审核，写审核人，写空更新时间
        wtaWalletTransactionAduit.setOperateBy(managerId);
        wtaWalletTransactionAduit.setUpdateBy(managerId);
        wtaWalletTransactionAduit.setUpdateTime(null);
        //至此审计表set完毕
        wafWalletAccountFund.setAvailableMoney(availableMoney);
        wafWalletAccountFund.setDepositingMoney(depositingMoney);
        wafWalletAccountFund.setWithdrawingMoney(withdrawingMoney);
        wafWalletAccountFund.setUpdateBy(managerId);
        wafWalletAccountFund.setUpdateTime(null);
        //至此fund表set完毕
        WtrWalletTransactionRecord wtrWalletTransactionRecord=wtrWalletTransactionRecordMapper.selectByPrimaryKey(wtaWalletTransactionAduit.getTransactionId());
        wtrWalletTransactionRecord.setTransactionId(null);
        //状态 1 -申请 , 2 -完成 , -3-失败
        wtrWalletTransactionRecord.setStatus((byte)2);
        wtrWalletTransactionRecord.setBalance(availableMoney);
        wtrWalletTransactionRecord.setCreateBy(managerId);
        wtrWalletTransactionRecord.setCreateTime(null);
        //钱包流水set完毕
        //修改审核
        wtaWalletTransactionAduitMapper.updateByPrimaryKeySelective(wtaWalletTransactionAduit);
        //修改钱包fund
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(wafWalletAccountFund);
        redisCache.setCacheObject("fundById"+wafWalletAccountFund.getBuyerId(),wafWalletAccountFund);
        //添加钱包流水
        wtrWalletTransactionRecordMapper.insertSelective(wtrWalletTransactionRecord);
        return true;
    }
    // 驳回单个钱包，缓存"fundById:"+buyerId
    @Override
    public boolean rejectAudit(String managerId,WtaWalletTransactionAduit wtaWalletTransactionAduit){
        WafWalletAccountFund wafWalletAccountFund=selectFundById(wtaWalletTransactionAduit.getBuyerId());
        // 充值前余额，充值中金额，提现中金额
        BigDecimal availableMoney=wafWalletAccountFund.getAvailableMoney();
        BigDecimal depositingMoney=wafWalletAccountFund.getDepositingMoney();
        BigDecimal withdrawingMoney=wafWalletAccountFund.getWithdrawingMoney();
        BigDecimal operateMoney=wtaWalletTransactionAduit.getOperateMoney();
        //设置好充值前余额，充值中金额，提现中金额
        wtaWalletTransactionAduit.setAvailableMoneyBefore(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyBefore(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyBefore(withdrawingMoney);
        //驳回，操作类型 1-充值 2-提现 3消费 4-退款
        if(wtaWalletTransactionAduit.getOperateType()==1){
            //充值，余额不变，充值中金额减，提现中不变
            depositingMoney=depositingMoney.subtract(operateMoney);
        }else if(wtaWalletTransactionAduit.getOperateType()==2){
            //提现，余额不变，充值中金额不变，提现中减
            withdrawingMoney=withdrawingMoney.subtract(operateMoney);
        }else if(wtaWalletTransactionAduit.getOperateType()==3){
            throw new RuntimeException("我不觉得你能驳回人家的消费");
        }else if(wtaWalletTransactionAduit.getOperateType()==4){
            throw new RuntimeException("我们真的有退款功能吗？");
        }
        wtaWalletTransactionAduit.setAvailableMoneyAfter(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyAfter(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyAfter(withdrawingMoney);
        //驳回审核，写审核人，写空更新时间
        wtaWalletTransactionAduit.setOperateBy(managerId);
        wtaWalletTransactionAduit.setUpdateBy(managerId);
        wtaWalletTransactionAduit.setUpdateTime(null);
        //至此审计表set完毕
        wafWalletAccountFund.setAvailableMoney(availableMoney);
        wafWalletAccountFund.setDepositingMoney(depositingMoney);
        wafWalletAccountFund.setWithdrawingMoney(withdrawingMoney);
        wafWalletAccountFund.setUpdateBy(managerId);
        wafWalletAccountFund.setUpdateTime(null);
        //至此fund表set完毕
        WtrWalletTransactionRecord wtrWalletTransactionRecord=wtrWalletTransactionRecordMapper.selectByPrimaryKey(wtaWalletTransactionAduit.getTransactionId());
        wtrWalletTransactionRecord.setTransactionId(null);
        //状态 1 -申请 , 2 -完成 , -3-失败
        wtrWalletTransactionRecord.setStatus((byte)3);
        wtrWalletTransactionRecord.setBalance(availableMoney);
        wtrWalletTransactionRecord.setCreateBy(managerId);
        wtrWalletTransactionRecord.setCreateTime(null);
        //钱包流水set完毕
        //修改审核
        wtaWalletTransactionAduitMapper.updateByPrimaryKeySelective(wtaWalletTransactionAduit);
        //修改钱包fund
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(wafWalletAccountFund);
        redisCache.setCacheObject("fundById"+wafWalletAccountFund.getBuyerId(),wafWalletAccountFund);
        //添加钱包流水
        wtrWalletTransactionRecordMapper.insertSelective(wtrWalletTransactionRecord);
        return true;
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
