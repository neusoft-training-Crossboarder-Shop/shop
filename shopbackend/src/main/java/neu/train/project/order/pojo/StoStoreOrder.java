package neu.train.project.order.pojo;

import neu.train.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class StoStoreOrder extends BaseEntity {
    private Integer stoId;

    private Integer proid;

    private Integer qty;

    private Integer storeId;

    private BigDecimal purchasePrice;

    private String paidTime;

    private Integer orderStatus;

    private Date createTime;

    private String createdBy;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    public StoStoreOrder(Integer stoId, Integer proid, Integer qty, Integer storeId, BigDecimal purchasePrice, String paidTime, Integer orderStatus, Date createTime, String createdBy, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd) {
        this.stoId = stoId;
        this.proid = proid;
        this.qty = qty;
        this.storeId = storeId;
        this.purchasePrice = purchasePrice;
        this.paidTime = paidTime;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
    }

    public StoStoreOrder() {
        super();
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime == null ? null : paidTime.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getCallCnt() {
        return callCnt;
    }

    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }

    public String getStsCd() {
        return stsCd;
    }

    public void setStsCd(String stsCd) {
        this.stsCd = stsCd == null ? null : stsCd.trim();
    }
}