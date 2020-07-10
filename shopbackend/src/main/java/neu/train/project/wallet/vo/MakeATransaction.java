package neu.train.project.wallet.vo;

import java.math.BigDecimal;

public class MakeATransaction {


    private String operateMoney;
    private Integer bankCardId;

    public MakeATransaction(String operateMoney, Integer bankCardId) {
        this.operateMoney = operateMoney;
        this.bankCardId = bankCardId;
    }

    public String getOperateMoney() {
        return operateMoney;
    }

    public void setOperateMoney(String operateMoney) {
        this.operateMoney = operateMoney;
    }

    public Integer getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }
}
