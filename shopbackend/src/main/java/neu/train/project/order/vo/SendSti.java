package neu.train.project.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SendSti {
    private Integer dilId;

    private Integer proId;

    private String title;

    private BigDecimal salPrice;

    private BigDecimal dropshipPrice;

    private Integer storeRetentionAmount;

    private Integer shelfStockAmount;

    private String dropshipStatus;

    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String remark;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    public SendSti(){}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        this.dropshipStatus = dropshipStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "SendSti{" +
                "dilId=" + dilId +
                ", proId=" + proId +
                ", title='" + title + '\'' +
                ", salPrice=" + salPrice +
                ", dropshipPrice=" + dropshipPrice +
                ", storeRetentionAmount=" + storeRetentionAmount +
                ", shelfStockAmount=" + shelfStockAmount +
                ", dropshipStatus='" + dropshipStatus + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
