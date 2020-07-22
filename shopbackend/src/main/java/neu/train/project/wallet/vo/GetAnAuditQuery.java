package neu.train.project.wallet.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

public class GetAnAuditQuery {

    private Integer buyerId;

    private Integer transactionId;

    private Byte operateType;

    private Byte status;
    @JsonIgnore
    private String beginTime;
    @JsonIgnore
    private String endTime;

    public GetAnAuditQuery(Integer buyerId, Integer transactionId, Byte operateType, Byte status, String beginTime, String endTime) {
        this.buyerId = buyerId;
        this.transactionId = transactionId;
        this.operateType = operateType;
        this.status = status;
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

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
