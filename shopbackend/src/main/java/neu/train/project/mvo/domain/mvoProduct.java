package neu.train.project.mvo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class mvoProduct implements Serializable {
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

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    private Boolean deleted;

    private String prostate;

    private String warranty;

    private mvoBrand productBrand;

    private List<mvoProductCategory> categories;

    private List<mvoImage> images;

    private List<mvoProductDescription> productDescriptions;

    private List<mvoPackageInfo> productPackageInfos;


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
        this.title = title;
    }

    public String getSkuCd() {
        return skuCd;
    }

    public void setSkuCd(String skuCd) {
        this.skuCd = skuCd;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
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

        this.warrantyDay = warrantyDay;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
        this.stsCd = stsCd;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getProstate() {
        return prostate;
    }

    public void setProstate(String prostate) {
        this.prostate = prostate;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        warranty.replace("<p>","");
        warranty.replace("</p>","");
        warranty.replace("<br>","");
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", proId=").append(proId);
        sb.append(", manId=").append(manId);
        sb.append(", brdId=").append(brdId);
        sb.append(", title=").append(title);
        sb.append(", skuCd=").append(skuCd);
        sb.append(", upc=").append(upc);
        sb.append(", ean=").append(ean);
        sb.append(", retailPrice=").append(retailPrice);
        sb.append(", mininumRetailPrice=").append(mininumRetailPrice);
        sb.append(", warrantyDay=").append(warrantyDay);
        sb.append(", timeUnit=").append(timeUnit);
        sb.append(", model=").append(model);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", callCnt=").append(callCnt);
        sb.append(", stsCd=").append(stsCd);
        sb.append(", deleted=").append(deleted);
        sb.append(", prostate=").append(prostate);
        sb.append(", warranty=").append(warranty);
        sb.append("]");
        return sb.toString();
    }

    public mvoBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(mvoBrand productBrand) {
        this.productBrand = productBrand;
    }

    public List<mvoProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<mvoProductCategory> categories) {
        this.categories = categories;
    }

    public List<mvoImage> getImages() {
        return images;
    }

    public void setImages(List<mvoImage> images) {
        this.images = images;
    }

    public List<mvoProductDescription> getProductDescriptions() {
        return productDescriptions;
    }

    public void setProductDescriptions(List<mvoProductDescription> productDescriptions) {
        this.productDescriptions = productDescriptions;
    }

    public List<mvoPackageInfo> getProductPackageInfos() {
        return productPackageInfos;
    }

    public void setProductPackageInfos(List<mvoPackageInfo> productPackageInfos) {
        this.productPackageInfos = productPackageInfos;
    }
}