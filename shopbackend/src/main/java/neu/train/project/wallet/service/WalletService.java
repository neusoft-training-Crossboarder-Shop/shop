package neu.train.project.wallet.service;

import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WafWalletAccountFund;

import java.util.List;

public interface WalletService {
    WaaWalletAccount selectWalletById(int buyerId);
    WafWalletAccountFund selectFundById(int buyerId);
    List<WaaWalletAccount> select(int buyerId,String accountName,String email);
    WaaWalletAccount insertWallet(WaaWalletAccount waaWalletAccount);
    WafWalletAccountFund insertFund(String currency);
    int test();
}
