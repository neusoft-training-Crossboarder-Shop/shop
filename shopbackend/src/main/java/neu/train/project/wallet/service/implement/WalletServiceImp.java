package neu.train.project.wallet.service.implement;

import neu.train.common.utils.SecurityUtils;
import neu.train.framework.redis.RedisCache;
import neu.train.project.wallet.mapper.WaaWalletAccountMapper;
import neu.train.project.wallet.mapper.WafWalletAccountFundMapper;
import neu.train.project.wallet.mapper.WtaWalletTransactionAduitMapper;
import neu.train.project.wallet.mapper.WtrWalletTransactionRecordMapper;
import neu.train.project.wallet.pojo.*;
import neu.train.project.wallet.service.WalletService;
import neu.train.project.wallet.vo.GetANewWallet;
import neu.train.project.wallet.vo.GetATransactionQuery;
import neu.train.project.wallet.vo.GetAnAuditQuery;
import neu.train.project.wallet.vo.MakeATransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.ch.WindowsAsynchronousFileChannelImpl;

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


    //查询有没有这个用户ID的钱包,缓存"walletById:"+buyerId
    @Override
    public boolean ifWallet(int buyerId) {
        if (redisCache.getCacheObject("walletById:" + buyerId) != null) {
            return true;
        }
        WaaWalletAccount waaWalletAccount = waaWalletAccountMapper.selectByPrimaryKey(buyerId);
        if (waaWalletAccount != null) {
            redisCache.setCacheObject("walletById:" + buyerId, waaWalletAccount);
            return true;
        } else {
            return false;
        }
    }

    //用于登录,缓存"walletById:"+buyerId
    @Override
    public boolean selectWalletLogin(int buyerId, String accountName, String password) {
       WaaWalletAccount waaWalletAccount=selectWalletById(buyerId);
        if(waaWalletAccount.getAccountName().equals(accountName) && SecurityUtils.matchesPassword(password,waaWalletAccount.getPassword())){
            return true;
        }
        return false;
    }

    //用于更新,缓存"walletById:"+buyerId
    @Override
    public boolean updateWallet(int buyerId,GetANewWallet getANewWallet) {
        WaaWalletAccount waaWalletAccount=selectWalletById(buyerId);
        if(waaWalletAccount==null){
            throw new RuntimeException("If NO,how YES?");
        }
        waaWalletAccount.setAccountName(getANewWallet.getAccountName());
        waaWalletAccount.setPassword(SecurityUtils.encryptPassword(getANewWallet.getPassword()));
        waaWalletAccount.setEmail(getANewWallet.getEmail());
        if (waaWalletAccountMapper.updateByPrimaryKeySelective(waaWalletAccount) == 0) {
            return false;
        } else {
            //再select是为了取得更新时间
            redisCache.setCacheObject("walletById:" + waaWalletAccount.getBuyerId(), waaWalletAccountMapper.selectByPrimaryKey(waaWalletAccount.getBuyerId()));
            return true;
        }
    }

    //用于注册,缓存"walletById:"+buyerId
    @Override
    public boolean insertWallet(int userId,int userType,GetANewWallet getANewWallet) {
        if (ifWallet(userId)) {
            throw new RuntimeException("You have owned a wallet,haven't you???");
        }
        WaaWalletAccount waaWalletAccount=new WaaWalletAccount();
        waaWalletAccount.setBuyerId(userId);
        waaWalletAccount.setAccountName(getANewWallet.getAccountName());
        waaWalletAccount.setEmail(getANewWallet.getEmail());
        waaWalletAccount.setPassword(SecurityUtils.encryptPassword(getANewWallet.getPassword()));
        waaWalletAccount.setCreatedBy(String.valueOf(userId));
        waaWalletAccount.setLastUpdateBy(String.valueOf(userId));
        waaWalletAccount.setIsActive("Y");
        waaWalletAccount.setStatus((byte) 7);
        waaWalletAccount.setHoldOrderTime("0");
        waaWalletAccount.setAutoPayStatus("0");
        waaWalletAccountMapper.insertSelective(waaWalletAccount);
        waaWalletAccount=waaWalletAccountMapper.selectByPrimaryKey(userId);
        redisCache.setCacheObject("walletById:" + userId, waaWalletAccount);
        return true;
    }

    //注册的时候，插入fund信息，缓存"fundById:"+buyerId
    @Override
    public boolean insertFund(int buyerId, String currency) {
        WafWalletAccountFund wafWalletAccountFund = new WafWalletAccountFund();
        wafWalletAccountFund.setBuyerId(buyerId);
        wafWalletAccountFund.setAvailableMoney(new BigDecimal(0));
        wafWalletAccountFund.setDepositingMoney(new BigDecimal(0));
        wafWalletAccountFund.setWithdrawingMoney(new BigDecimal(0));
        wafWalletAccountFund.setCreatedBy(String.valueOf(buyerId));
        wafWalletAccountFund.setLastUpdateBy(String.valueOf(buyerId));
        wafWalletAccountFund.setCurrency(currency);
        wafWalletAccountFundMapper.insertSelective(wafWalletAccountFund);
        wafWalletAccountFund=wafWalletAccountFundMapper.selectByPrimaryKey(buyerId);
        redisCache.setCacheObject("fundById:" + buyerId, wafWalletAccountFund);
        return true;
    }

    //根据用户Id，检索钱包，缓存"walletById:"+buyerId
    @Override
    public WaaWalletAccount selectWalletById(int buyerId) {
        if(ifWallet(buyerId)){
            return redisCache.getCacheObject("walletById:" + buyerId);
        }else{
          throw new RuntimeException("You have no wallet,come and get you one!");
        }
    }

    //根据用户Id，检索钱包余额之类的东西，缓存"fundById:"+buyerId
    @Override
    public WafWalletAccountFund selectFundById(int buyerId) {
        WafWalletAccountFund wafWalletAccountFund = redisCache.getCacheObject("fundById:" + buyerId);
        if (wafWalletAccountFund != null) {
            return wafWalletAccountFund;
        }
        wafWalletAccountFund = wafWalletAccountFundMapper.selectByPrimaryKey(buyerId);
        redisCache.setCacheObject("fundById:" + buyerId, wafWalletAccountFund);
        return wafWalletAccountFund;
    }

    //多条件查询钱包流水，不缓存
    @Override
    public List<WtrWalletTransactionRecord> selectTransaction(GetATransactionQuery getATransactionQuery) {
        return wtrWalletTransactionRecordMapper.selectByMany(getATransactionQuery);
    }

    //多条件查询流水审批，不缓存
    @Override
    public List<WtaWalletTransactionAduit> selectAudit(GetAnAuditQuery getAnAuditQuery) {
        return wtaWalletTransactionAduitMapper.selectByMany(getAnAuditQuery);
    }

    //批量同意审核
    @Override
    public boolean doAudits(String managerId, Integer[] ids) {
        List<WtaWalletTransactionAduit> wtaWalletTransactionAduits = wtaWalletTransactionAduitMapper.selectByIds(ids);
        for (WtaWalletTransactionAduit wtaWalletTransactionAduit : wtaWalletTransactionAduits) {
            doAudit(managerId, wtaWalletTransactionAduit);
        }
        return true;
    }

    //批量驳回审核
    @Override
    public boolean rejectAudits(String managerId, Integer[] ids) {
        List<WtaWalletTransactionAduit> wtaWalletTransactionAduits = wtaWalletTransactionAduitMapper.selectByIds(ids);
        for (WtaWalletTransactionAduit wtaWalletTransactionAduit : wtaWalletTransactionAduits) {
                rejectAudit(managerId, wtaWalletTransactionAduit);
        }
        return true;
    }


    //同意单个钱包，缓存"fundById:"+buyerId
    @Override
    public boolean doAudit(String managerId, WtaWalletTransactionAduit wtaWalletTransactionAduit) {
        if(wtaWalletTransactionAduit.getStatus()!=1){
            throw new RuntimeException("You've already done this!");
        }
        WafWalletAccountFund wafWalletAccountFund = selectFundById(wtaWalletTransactionAduit.getBuyerId());
        // 充值前余额，充值中金额，提现中金额
        BigDecimal availableMoney = wafWalletAccountFund.getAvailableMoney();
        BigDecimal depositingMoney = wafWalletAccountFund.getDepositingMoney();
        BigDecimal withdrawingMoney = wafWalletAccountFund.getWithdrawingMoney();
        BigDecimal operateMoney = wtaWalletTransactionAduit.getOperateMoney();
        //设置好充值前余额，充值中金额，提现中金额
        wtaWalletTransactionAduit.setAvailableMoneyBefore(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyBefore(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyBefore(withdrawingMoney);
        //操作类型 1-充值 2-提现 3消费 4-退款
        if (wtaWalletTransactionAduit.getOperateType() == 1) {
            //充值，余额加，充值中金额减，提现中不变
            availableMoney = availableMoney.add(operateMoney);
            depositingMoney = depositingMoney.subtract(operateMoney);
        } else if (wtaWalletTransactionAduit.getOperateType() == 2) {
            //提现，余额减，充值中金额不变，提现中减
            availableMoney = availableMoney.subtract(operateMoney);
            withdrawingMoney = withdrawingMoney.subtract(operateMoney);
        } else if (wtaWalletTransactionAduit.getOperateType() == 3) {
            //消费，余额减，充值中金额不变，提现中不变
            availableMoney = availableMoney.subtract(operateMoney);
        } else if (wtaWalletTransactionAduit.getOperateType() == 4) {
            //退款，余额加，充值中金额不变，提现中不变
            availableMoney = availableMoney.add(operateMoney);
        }
        wtaWalletTransactionAduit.setAvailableMoneyAfter(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyAfter(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyAfter(withdrawingMoney);
        //通过审核，写审核人
        wtaWalletTransactionAduit.setStatus((byte)2);
        wtaWalletTransactionAduit.setOperateBy(managerId);
        wtaWalletTransactionAduit.setLastUpdateBy(managerId);
        wtaWalletTransactionAduit.setLastUpdateTime(null);
        //至此审计表set完毕
        wafWalletAccountFund.setAvailableMoney(availableMoney);
        wafWalletAccountFund.setDepositingMoney(depositingMoney);
        wafWalletAccountFund.setWithdrawingMoney(withdrawingMoney);
        wafWalletAccountFund.setLastUpdateBy(managerId);
        wafWalletAccountFund.setLastUpdateTime(null);
        //至此fund表set完毕
        WtrWalletTransactionRecord wtrWalletTransactionRecord = wtrWalletTransactionRecordMapper.selectByPrimaryKey(wtaWalletTransactionAduit.getTransactionId());
        wtrWalletTransactionRecord.setTransactionId(null);
        //状态 1 -申请 , 2 -完成 , -3-失败
        wtrWalletTransactionRecord.setStatus((byte) 2);
        wtrWalletTransactionRecord.setBalance(availableMoney);
        //重写创建时间
        wtrWalletTransactionRecord.setCreateTime(null);
        //钱包流水set完毕
        //乐观锁
        canOperate(wafWalletAccountFund.getBuyerId(),wafWalletAccountFund.getVersion());
        wafWalletAccountFund.setVersion(wafWalletAccountFund.getVersion()+1);
        //修改审核
        wtaWalletTransactionAduitMapper.updateByPrimaryKeySelective(wtaWalletTransactionAduit);
        //修改钱包fund
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(wafWalletAccountFund);
        redisCache.setCacheObject("fundById:" + wafWalletAccountFund.getBuyerId(), wafWalletAccountFundMapper.selectByPrimaryKey(wafWalletAccountFund.getBuyerId()));
        //添加钱包流水
        wtrWalletTransactionRecordMapper.insertSelective(wtrWalletTransactionRecord);
        return true;
    }

    // 驳回单个钱包，缓存"fundById:"+buyerId
    @Override
    public boolean rejectAudit(String managerId, WtaWalletTransactionAduit wtaWalletTransactionAduit) {
        if(wtaWalletTransactionAduit.getStatus()!=1){
            throw new RuntimeException("You've already done this!");
        }
        WafWalletAccountFund wafWalletAccountFund = selectFundById(wtaWalletTransactionAduit.getBuyerId());
        // 充值前余额，充值中金额，提现中金额
        BigDecimal availableMoney = wafWalletAccountFund.getAvailableMoney();
        BigDecimal depositingMoney = wafWalletAccountFund.getDepositingMoney();
        BigDecimal withdrawingMoney = wafWalletAccountFund.getWithdrawingMoney();
        BigDecimal operateMoney = wtaWalletTransactionAduit.getOperateMoney();
        //设置好充值前余额，充值中金额，提现中金额
        wtaWalletTransactionAduit.setAvailableMoneyBefore(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyBefore(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyBefore(withdrawingMoney);
        //驳回，操作类型 1-充值 2-提现 3消费 4-退款
        if (wtaWalletTransactionAduit.getOperateType() == 1) {
            //充值，余额不变，充值中金额减，提现中不变
            depositingMoney = depositingMoney.subtract(operateMoney);
        } else if (wtaWalletTransactionAduit.getOperateType() == 2) {
            //提现，余额不变，充值中金额不变，提现中减
            withdrawingMoney = withdrawingMoney.subtract(operateMoney);
        } else if (wtaWalletTransactionAduit.getOperateType() == 3) {
            throw new RuntimeException("You can not cancel others' payment");
        } else if (wtaWalletTransactionAduit.getOperateType() == 4) {
            throw new RuntimeException("Do we really have this function?I mean:refund???");
        }
        wtaWalletTransactionAduit.setAvailableMoneyAfter(availableMoney);
        wtaWalletTransactionAduit.setDepositingMoneyAfter(depositingMoney);
        wtaWalletTransactionAduit.setWithdrawingMoneyAfter(withdrawingMoney);
        //驳回审核，写审核人
        wtaWalletTransactionAduit.setStatus((byte)3);
        wtaWalletTransactionAduit.setOperateBy(managerId);
        wtaWalletTransactionAduit.setLastUpdateBy(managerId);
        wtaWalletTransactionAduit.setLastUpdateTime(null);
        //至此审计表set完毕
        wafWalletAccountFund.setAvailableMoney(availableMoney);
        wafWalletAccountFund.setDepositingMoney(depositingMoney);
        wafWalletAccountFund.setWithdrawingMoney(withdrawingMoney);
        wafWalletAccountFund.setLastUpdateBy(managerId);
        wafWalletAccountFund.setLastUpdateTime(null);
        //至此fund表set完毕
        WtrWalletTransactionRecord wtrWalletTransactionRecord = wtrWalletTransactionRecordMapper.selectByPrimaryKey(wtaWalletTransactionAduit.getTransactionId());
        wtrWalletTransactionRecord.setTransactionId(null);
        //状态 1 -申请 , 2 -完成 , -3-失败
        wtrWalletTransactionRecord.setStatus((byte) 3);
        wtrWalletTransactionRecord.setBalance(availableMoney);
        //重写创建时间
        wtrWalletTransactionRecord.setCreateTime(null);
        //钱包流水set完毕
        //乐观锁
        canOperate(wafWalletAccountFund.getBuyerId(),wafWalletAccountFund.getVersion());
        wafWalletAccountFund.setVersion(wafWalletAccountFund.getVersion()+1);
        //修改审核
        wtaWalletTransactionAduitMapper.updateByPrimaryKeySelective(wtaWalletTransactionAduit);
        //修改钱包fund
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(wafWalletAccountFund);
        redisCache.setCacheObject("fundById:" + wafWalletAccountFund.getBuyerId(), wafWalletAccountFundMapper.selectByPrimaryKey(wafWalletAccountFund.getBuyerId()));
        //添加钱包流水
        wtrWalletTransactionRecordMapper.insertSelective(wtrWalletTransactionRecord);
        return true;
    }

    //充值，缓存"fundById:"+buyerId
    @Override
    public boolean tryDeposit(int buyerId, MakeATransaction makeATransaction) {
        //直接修改fund
        BigDecimal money = new BigDecimal(makeATransaction.getOperateMoney());
        WafWalletAccountFund wafWalletAccountFund = selectFundById(buyerId);
        wafWalletAccountFund.setDepositingMoney(wafWalletAccountFund.getDepositingMoney().add(money));
        wafWalletAccountFund.setLastUpdateBy(String.valueOf(buyerId));
        wafWalletAccountFund.setLastUpdateTime(null);
        //乐观锁
        canOperate(wafWalletAccountFund.getBuyerId(),wafWalletAccountFund.getVersion());
        wafWalletAccountFund.setVersion(wafWalletAccountFund.getVersion()+1);
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(wafWalletAccountFund);
        redisCache.setCacheObject("fundById:" + wafWalletAccountFund.getBuyerId(), wafWalletAccountFundMapper.selectByPrimaryKey(buyerId));
        //先插入一条transaction,主键，创建时间自动生成
        WtrWalletTransactionRecord wtrWalletTransactionRecord = new WtrWalletTransactionRecord();
        wtrWalletTransactionRecord.setBuyerId(buyerId);
        wtrWalletTransactionRecord.setBankCardId(makeATransaction.getBankCardId());
        wtrWalletTransactionRecord.setTransactionType((byte) 1);
        wtrWalletTransactionRecord.setTransactionMoney(money);
        wtrWalletTransactionRecord.setStatus((byte) 1);
        wtrWalletTransactionRecord.setBalance(wafWalletAccountFund.getAvailableMoney());
        wtrWalletTransactionRecord.setFinanceType((byte) 1);
        wtrWalletTransactionRecordMapper.insertSelective(wtrWalletTransactionRecord);
        //插入一条audit,主键，创建时间自动生成，所有未来数据为空
        WtaWalletTransactionAduit wtaWalletTransactionAduit = new WtaWalletTransactionAduit();
        wtaWalletTransactionAduit.setBuyerId(buyerId);
        wtaWalletTransactionAduit.setTransactionId(wtrWalletTransactionRecord.getTransactionId());
        wtaWalletTransactionAduit.setOperateMoney(money);
        wtaWalletTransactionAduit.setOperateType((byte) 1);
        wtaWalletTransactionAduit.setOperateBy(String.valueOf(buyerId));
        wtaWalletTransactionAduit.setStatus((byte) 1);
        wtaWalletTransactionAduit.setCreatedBy(String.valueOf(buyerId));
        wtaWalletTransactionAduit.setLastUpdateBy(String.valueOf(buyerId));
        wtaWalletTransactionAduitMapper.insertSelective(wtaWalletTransactionAduit);
        return true;
    }


    //提现，缓存"fundById:"+buyerId
    @Override
    public boolean tryWithdraw(int buyerId, MakeATransaction makeATransaction) {
        //直接修改fund
        BigDecimal money = new BigDecimal(makeATransaction.getOperateMoney());
        WafWalletAccountFund wafWalletAccountFund = selectFundById(buyerId);
        BigDecimal newWithDrawingMoney=wafWalletAccountFund.getWithdrawingMoney().add(money);
        if(wafWalletAccountFund.getAvailableMoney().compareTo(newWithDrawingMoney) < 0){
            throw new RuntimeException("Not Enough Minerals!");
        }
        wafWalletAccountFund.setWithdrawingMoney(newWithDrawingMoney);
        wafWalletAccountFund.setUpdateBy(String.valueOf(buyerId));
        wafWalletAccountFund.setLastUpdateTime(null);
        //乐观锁
        canOperate(wafWalletAccountFund.getBuyerId(),wafWalletAccountFund.getVersion());
        wafWalletAccountFund.setVersion(wafWalletAccountFund.getVersion()+1);
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(wafWalletAccountFund);
        redisCache.setCacheObject("fundById:" + wafWalletAccountFund.getBuyerId(), wafWalletAccountFundMapper.selectByPrimaryKey(buyerId));
        //先插入一条transaction,主键，创建时间自动生成
        WtrWalletTransactionRecord wtrWalletTransactionRecord = new WtrWalletTransactionRecord();
        wtrWalletTransactionRecord.setBuyerId(buyerId);
        wtrWalletTransactionRecord.setBankCardId(makeATransaction.getBankCardId());
        wtrWalletTransactionRecord.setTransactionType((byte) 2);
        wtrWalletTransactionRecord.setTransactionMoney(money);
        wtrWalletTransactionRecord.setStatus((byte) 1);
        wtrWalletTransactionRecord.setBalance(wafWalletAccountFund.getAvailableMoney());
        wtrWalletTransactionRecord.setFinanceType((byte) 2);
        wtrWalletTransactionRecord.setCreateBy(String.valueOf(buyerId));
        wtrWalletTransactionRecord.setUpdateBy(String.valueOf(buyerId));
        wtrWalletTransactionRecordMapper.insertSelective(wtrWalletTransactionRecord);
        //插入一条audit,主键，创建时间自动生成，所有未来数据为空
        WtaWalletTransactionAduit wtaWalletTransactionAduit = new WtaWalletTransactionAduit();
        wtaWalletTransactionAduit.setBuyerId(buyerId);
        wtaWalletTransactionAduit.setTransactionId(wtrWalletTransactionRecord.getTransactionId());
        wtaWalletTransactionAduit.setOperateMoney(money);
        wtaWalletTransactionAduit.setOperateType((byte) 2);
        wtaWalletTransactionAduit.setOperateBy(String.valueOf(buyerId));
        wtaWalletTransactionAduit.setStatus((byte) 1);
        wtaWalletTransactionAduit.setCreatedBy(String.valueOf(buyerId));
        wtaWalletTransactionAduit.setLastUpdateBy(String.valueOf(buyerId));
        wtaWalletTransactionAduitMapper.insertSelective(wtaWalletTransactionAduit);
        return true;
    }

    //其实在支付一个原始订单，改余额，加流水，缓存"fundById:"+buyerId
    @Override
    public boolean pay(int bvoId, int mvoId, BigDecimal total){
       selectWalletById(bvoId);
       selectWalletById(mvoId);
       WafWalletAccountFund bvoFund=selectFundById(bvoId);
       WafWalletAccountFund mvoFund=selectFundById(mvoId);
       //钱不够
       if(bvoFund.getAvailableMoney().compareTo(total) < 0){
           throw new RuntimeException("Insufficient funds!");
       }
       //转款，直接写流水，避开审核
        bvoFund.setAvailableMoney(bvoFund.getAvailableMoney().subtract(total));
       bvoFund.setLastUpdateBy(String.valueOf(bvoId));
       bvoFund.setLastUpdateTime(null);
       mvoFund.setAvailableMoney(mvoFund.getAvailableMoney().add(total));
       mvoFund.setLastUpdateBy(String.valueOf(mvoId));
       mvoFund.setLastUpdateTime(null);
        //乐观锁
        canOperate(bvoFund.getBuyerId(),bvoFund.getVersion());
        bvoFund.setVersion(bvoFund.getVersion()+1);
        canOperate(mvoFund.getBuyerId(),mvoFund.getVersion());
        mvoFund.setVersion(mvoFund.getVersion()+1);
       wafWalletAccountFundMapper.updateByPrimaryKeySelective(bvoFund);
        wafWalletAccountFundMapper.updateByPrimaryKeySelective(mvoFund);
        redisCache.setCacheObject("fundById:" + bvoId, wafWalletAccountFundMapper.selectByPrimaryKey(bvoId));
        redisCache.setCacheObject("fundById:" + mvoId, wafWalletAccountFundMapper.selectByPrimaryKey(mvoId));
        //写bvo流水
        WtrWalletTransactionRecord bvoTransaction=new WtrWalletTransactionRecord();
        bvoTransaction.setBuyerId(bvoId);
        bvoTransaction.setTransactionType((byte)3);
        bvoTransaction.setTransactionMoney(total.negate());
        bvoTransaction.setStatus((byte)2);
        bvoTransaction.setBalance(bvoFund.getAvailableMoney());
        bvoTransaction.setFinanceType((byte)2);
        wtrWalletTransactionRecordMapper.insertSelective(bvoTransaction);
        //写mvo流水
        WtrWalletTransactionRecord mvoTransaction=new WtrWalletTransactionRecord();
        mvoTransaction.setBuyerId(mvoId);
        mvoTransaction.setTransactionType((byte)3);
        mvoTransaction.setTransactionMoney(total);
        mvoTransaction.setStatus((byte)2);
        mvoTransaction.setBalance(mvoFund.getAvailableMoney());
        mvoTransaction.setFinanceType((byte)1);
        wtrWalletTransactionRecordMapper.insertSelective(mvoTransaction);
        return true;
    }


    public void canOperate(int fundId,int version){
        WafWalletAccountFund wafWalletAccountFund=selectFundById(fundId);
        if(wafWalletAccountFund.getVersion()!=version){
            throw new RuntimeException("Data out-of-date");
        }
    }

}
