package neu.train.project.wallet.vo;

import java.math.BigDecimal;

public class MakeATransaction {


    private String operateMoney;
    private String bankCardId;

    public MakeATransaction(String operateMoney, String bankCardId) {
        this.operateMoney = operateMoney;
        this.bankCardId = bankCardId;
    }

    public String getOperateMoney() {
        return operateMoney;
    }

    public void setOperateMoney(String operateMoney) {
        this.operateMoney = operateMoney;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }
}
