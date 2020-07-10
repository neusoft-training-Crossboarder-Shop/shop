package neu.train.project.wallet.service;

import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WafWalletAccountFund;
import neu.train.project.wallet.pojo.WtrWalletTransactionRecord;
import neu.train.project.wallet.vo.GetATransactionQuery;

import java.util.List;

public interface WalletService {
    boolean ifWallet(int buyerId);
    WaaWalletAccount selectWalletById(int buyerId);
    boolean selectWalletLogin(int buyerId,String accountName,String password);
    boolean updateWallet(WaaWalletAccount waaWalletAccount);
    boolean insertWallet(WaaWalletAccount waaWalletAccount);
    boolean insertFund(int buyerId,String currency);
    List<WtrWalletTransactionRecord> selectTransaction(GetATransactionQuery getATransactionQuery);




    WafWalletAccountFund selectFundById(int buyerId);

    boolean test(int num);
}
