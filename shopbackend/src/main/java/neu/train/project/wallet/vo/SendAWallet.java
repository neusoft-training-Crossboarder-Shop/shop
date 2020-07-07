package neu.train.project.wallet.vo;

import neu.train.project.wallet.pojo.WaaWalletAccount;
import neu.train.project.wallet.pojo.WafWalletAccountFund;

public class SendAWallet {
    private WaaWalletAccount waaWalletAccount;
    private WafWalletAccountFund wafWalletAccountFund;

    public SendAWallet(WaaWalletAccount waaWalletAccount, WafWalletAccountFund wafWalletAccountFund) {
        this.waaWalletAccount = waaWalletAccount;
        this.wafWalletAccountFund = wafWalletAccountFund;
    }

    public WaaWalletAccount getWaaWalletAccount() {
        return waaWalletAccount;
    }

    public void setWaaWalletAccount(WaaWalletAccount waaWalletAccount) {
        this.waaWalletAccount = waaWalletAccount;
    }

    public WafWalletAccountFund getWafWalletAccountFund() {
        return wafWalletAccountFund;
    }

    public void setWafWalletAccountFund(WafWalletAccountFund wafWalletAccountFund) {
        this.wafWalletAccountFund = wafWalletAccountFund;
    }
}
