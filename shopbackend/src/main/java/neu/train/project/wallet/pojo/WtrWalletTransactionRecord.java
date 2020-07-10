package neu.train.project.wallet.pojo;

import neu.train.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class WtrWalletTransactionRecord extends BaseEntity {
    private Integer transactionId;

    private Integer buyerId;

    private Integer bankCardId;

    private Byte transactionType;

    private BigDecimal transactionMoney;

    private Byte status;

    private BigDecimal balance;

    private Integer businessId;

    private Byte financeType;

    private Date createTime;

    public WtrWalletTransactionRecord(Integer transactionId, Integer buyerId, Integer bankCardId, Byte transactionType, BigDecimal transactionMoney, Byte status, BigDecimal balance, Integer businessId, Byte financeType, Date createTime) {
        this.transactionId = transactionId;
        this.buyerId = buyerId;
        this.bankCardId = bankCardId;
        this.transactionType = transactionType;
        this.transactionMoney = transactionMoney;
        this.status = status;
        this.balance = balance;
        this.businessId = businessId;
        this.financeType = financeType;
        this.createTime = createTime;
    }

    public WtrWalletTransactionRecord() {
        super();
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }

    public Byte getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Byte transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(BigDecimal transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Byte getFinanceType() {
        return financeType;
    }

    public void setFinanceType(Byte financeType) {
        this.financeType = financeType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}