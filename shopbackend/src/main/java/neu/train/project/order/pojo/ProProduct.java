package neu.train.project.order.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ProProduct {
    private Integer proId;

    private Integer manId;

    private Integer brdId;

    private String title;

    private String skuCd;

    private String upc;

    private String ean;

    private BigDecimal retailPrice;

    private BigDecimal mininumRetailPrice;

    private String warrantyDay;

    private String timeUnit;

    private String model;

    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    private String warranty;

    public ProProduct(Integer proId, Integer manId, Integer brdId, String title, String skuCd, String upc, String ean, BigDecimal retailPrice, BigDecimal mininumRetailPrice, String warrantyDay, String timeUnit, String model, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd, String warranty) {
        this.proId = proId;
        this.manId = manId;
        this.brdId = brdId;
        this.title = title;
        this.skuCd = skuCd;
        this.upc = upc;
        this.ean = ean;
        this.retailPrice = retailPrice;
        this.mininumRetailPrice = mininumRetailPrice;
        this.warrantyDay = warrantyDay;
        this.timeUnit = timeUnit;
        this.model = model;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
        this.warranty = warranty;
    }

    public ProProduct() {
        super();
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }

    public Integer getBrdId() {
        return brdId;
    }

    public void setBrdId(Integer brdId) {
        this.brdId = brdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSkuCd() {
        return skuCd;
    }

    public void setSkuCd(String skuCd) {
        this.skuCd = skuCd == null ? null : skuCd.trim();
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc == null ? null : upc.trim();
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean == null ? null : ean.trim();
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMininumRetailPrice() {
        return mininumRetailPrice;
    }

    public void setMininumRetailPrice(BigDecimal mininumRetailPrice) {
        this.mininumRetailPrice = mininumRetailPrice;
    }

    public String getWarrantyDay() {
        return warrantyDay;
    }

    public void setWarrantyDay(String warrantyDay) {
        this.warrantyDay = warrantyDay == null ? null : warrantyDay.trim();
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit == null ? null : timeUnit.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
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

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty == null ? null : warranty.trim();
    }
}