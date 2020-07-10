package neu.train.project.wallet.vo;

import java.math.BigDecimal;
import java.util.Date;

public class GetATransactionQuery {
    private Integer buyerId;

    private Integer transactionId;

    private Integer bankCardId;

    private Byte transactionType;

    private Byte status;

    private Byte financeType;

    private Date beginTime;

    private Date endTime;

    public GetATransactionQuery(Integer buyerId, Integer transactionId, Integer bankCardId, Byte transactionType, Byte status, Byte financeType, Date beginTime, Date endTime) {
        this.buyerId = buyerId;
        this.transactionId = transactionId;
        this.bankCardId = bankCardId;
        this.transactionType = transactionType;
        this.status = status;
        this.financeType = financeType;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getFinanceType() {
        return financeType;
    }

    public void setFinanceType(Byte financeType) {
        this.financeType = financeType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
