package neu.train.project.wallet.vo;

import java.math.BigDecimal;
import java.util.Date;

public class GetAnAuditQuery {

    private Integer buyerId;

    private Integer transactionId;

    private Byte operateType;

    private Byte status;

    private Date beginTime;

    private Date endTime;

    public GetAnAuditQuery(Integer buyerId, Integer transactionId, Byte operateType, Byte status, Date beginTime, Date endTime) {
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
