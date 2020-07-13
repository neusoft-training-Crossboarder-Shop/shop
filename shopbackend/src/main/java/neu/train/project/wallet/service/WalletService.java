package neu.train.project.wallet.service;

import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WafWalletAccountFund;
import neu.train.project.wallet.pojo.WtaWalletTransactionAduit;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.vo.GetATransactionQuery;
import neu.train.project.wallet.vo.GetAnAuditQuery;
import neu.train.project.wallet.vo.MakeATransaction;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    boolean ifWallet(int buyerId);
    WaaWalletAccount selectWalletById(int buyerId);
    boolean selectWalletLogin(int buyerId,String accountName,String password);
    boolean updateWallet(WaaWalletAccount waaWalletAccount);
    boolean insertWallet(WaaWalletAccount waaWalletAccount);
    WafWalletAccountFund selectFundById(int buyerId);
    boolean insertFund(int buyerId,String currency);
    List<WtrWalletTransactionRecord> selectTransaction(GetATransactionQuery getATransactionQuery);
    List<WtaWalletTransactionAduit> selectAudit(GetAnAuditQuery getAnAuditQuery);
    boolean doAudits(String managerId,Integer[] ids);
    boolean doAudit(String managerId,WtaWalletTransactionAduit wtaWalletTransactionAduit);
    boolean rejectAudits(String managerId, Integer[] ids);
    boolean rejectAudit(String managerId,WtaWalletTransactionAduit wtaWalletTransactionAduit);
    boolean tryDeposit(int buyerId,MakeATransaction makeATransaction);
    boolean tryWithdraw(int buyerId,MakeATransaction makeATransaction);
    boolean pay(int bvoId, int mvoId, BigDecimal total);


}
