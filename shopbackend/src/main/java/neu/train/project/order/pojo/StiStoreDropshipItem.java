package neu.train.project.order.pojo;

import neu.train.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class StiStoreDropshipItem extends BaseEntity {
    private Integer dilId;

    private Integer proId;

    private Integer strId;

    private BigDecimal salPrice;

    private BigDecimal dropshipPrice;

    private Integer storeRetentionAmount;

    private Integer shelfStockAmount;

    private String dropshipStatus;

    private String createdBy;

    private Date createTime;

    private Integer callCnt;

    private String remark;

    private String stsCd;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    public StiStoreDropshipItem(Integer dilId, Integer proId, Integer strId, BigDecimal salPrice, BigDecimal dropshipPrice, Integer storeRetentionAmount, Integer shelfStockAmount, String dropshipStatus, String createdBy, Date createTime, Integer callCnt, String remark, String stsCd, String lastUpdateBy, Date lastUpdateTime) {
        this.dilId = dilId;
        this.proId = proId;
        this.strId = strId;
        this.salPrice = salPrice;
        this.dropshipPrice = dropshipPrice;
        this.storeRetentionAmount = storeRetentionAmount;
        this.shelfStockAmount = shelfStockAmount;
        this.dropshipStatus = dropshipStatus;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.callCnt = callCnt;
        this.remark = remark;
        this.stsCd = stsCd;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
    }

    public StiStoreDropshipItem() {
        super();
    }

    public Integer getDilId() {
        return dilId;
    }

    public void setDilId(Integer dilId) {
        this.dilId = dilId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getStrId() {
        return strId;
    }

    public void setStrId(Integer strId) {
        this.strId = strId;
    }

    public BigDecimal getSalPrice() {
        return salPrice;
    }

    public void setSalPrice(BigDecimal salPrice) {
        this.salPrice = salPrice;
    }

    public BigDecimal getDropshipPrice() {
        return dropshipPrice;
    }

    public void setDropshipPrice(BigDecimal dropshipPrice) {
        this.dropshipPrice = dropshipPrice;
    }

    public Integer getStoreRetentionAmount() {
        return storeRetentionAmount;
    }

    public void setStoreRetentionAmount(Integer storeRetentionAmount) {
        this.storeRetentionAmount = storeRetentionAmount;
    }

    public Integer getShelfStockAmount() {
        return shelfStockAmount;
    }

    public void setShelfStockAmount(Integer shelfStockAmount) {
        this.shelfStockAmount = shelfStockAmount;
    }

    public String getDropshipStatus() {
        return dropshipStatus;
    }

    public void setDropshipStatus(String dropshipStatus) {
        this.dropshipStatus = dropshipStatus == null ? null : dropshipStatus.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCallCnt() {
        return callCnt;
    }

    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStsCd() {
        return stsCd;
    }

    public void setStsCd(String stsCd) {
        this.stsCd = stsCd == null ? null : stsCd.trim();
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
}