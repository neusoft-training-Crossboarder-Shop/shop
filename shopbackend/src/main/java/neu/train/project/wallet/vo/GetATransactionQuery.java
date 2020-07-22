package neu.train.project.wallet.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

public class GetATransactionQuery {
    private Integer buyerId;

    private Integer transactionId;

    private String bankCardId;

    private Byte transactionType;

    private Byte status;

    private Byte financeType;
    @JsonIgnore
    private String beginTime;
    @JsonIgnore
    private String endTime;

    public GetATransactionQuery(Integer buyerId, Integer transactionId, String bankCardId, Byte transactionType, Byte status, Byte financeType, String beginTime, String endTime) {
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

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
