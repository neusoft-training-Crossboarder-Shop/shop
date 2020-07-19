package neu.train.project.mvo.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ProProduct {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.pro_id
     *
     * @mbg.generated
     */
    private Integer proId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.man_id
     *
     * @mbg.generated
     */
    private Integer manId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.brd_id
     *
     * @mbg.generated
     */
    private Integer brdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.sku_cd
     *
     * @mbg.generated
     */
    private String skuCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.upc
     *
     * @mbg.generated
     */
    private String upc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.ean
     *
     * @mbg.generated
     */
    private String ean;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.retail_price
     *
     * @mbg.generated
     */
    private BigDecimal retailPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.mininum_retail_price
     *
     * @mbg.generated
     */
    private BigDecimal mininumRetailPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.warranty_day
     *
     * @mbg.generated
     */
    private String warrantyDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.time_unit
     *
     * @mbg.generated
     */
    private String timeUnit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.model
     *
     * @mbg.generated
     */
    private String model;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.created_by
     *
     * @mbg.generated
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.last_update_by
     *
     * @mbg.generated
     */
    private String lastUpdateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.last_update_time
     *
     * @mbg.generated
     */
    private LocalDateTime lastUpdateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.call_cnt
     *
     * @mbg.generated
     */
    private Integer callCnt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.sts_cd
     *
     * @mbg.generated
     */
    private String stsCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_product.warranty
     *
     * @mbg.generated
     */
    private String warranty;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.pro_id
     *
     * @return the value of pro_product.pro_id
     *
     * @mbg.generated
     */
    public Integer getProId() {
        return proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.pro_id
     *
     * @param proId the value for pro_product.pro_id
     *
     * @mbg.generated
     */
    public void setProId(Integer proId) {
        this.proId = proId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.man_id
     *
     * @return the value of pro_product.man_id
     *
     * @mbg.generated
     */
    public Integer getManId() {
        return manId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.man_id
     *
     * @param manId the value for pro_product.man_id
     *
     * @mbg.generated
     */
    public void setManId(Integer manId) {
        this.manId = manId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.brd_id
     *
     * @return the value of pro_product.brd_id
     *
     * @mbg.generated
     */
    public Integer getBrdId() {
        return brdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.brd_id
     *
     * @param brdId the value for pro_product.brd_id
     *
     * @mbg.generated
     */
    public void setBrdId(Integer brdId) {
        this.brdId = brdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.title
     *
     * @return the value of pro_product.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.title
     *
     * @param title the value for pro_product.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.sku_cd
     *
     * @return the value of pro_product.sku_cd
     *
     * @mbg.generated
     */
    public String getSkuCd() {
        return skuCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.sku_cd
     *
     * @param skuCd the value for pro_product.sku_cd
     *
     * @mbg.generated
     */
    public void setSkuCd(String skuCd) {
        this.skuCd = skuCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.upc
     *
     * @return the value of pro_product.upc
     *
     * @mbg.generated
     */
    public String getUpc() {
        return upc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.upc
     *
     * @param upc the value for pro_product.upc
     *
     * @mbg.generated
     */
    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.ean
     *
     * @return the value of pro_product.ean
     *
     * @mbg.generated
     */
    public String getEan() {
        return ean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.ean
     *
     * @param ean the value for pro_product.ean
     *
     * @mbg.generated
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.retail_price
     *
     * @return the value of pro_product.retail_price
     *
     * @mbg.generated
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.retail_price
     *
     * @param retailPrice the value for pro_product.retail_price
     *
     * @mbg.generated
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.mininum_retail_price
     *
     * @return the value of pro_product.mininum_retail_price
     *
     * @mbg.generated
     */
    public BigDecimal getMininumRetailPrice() {
        return mininumRetailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.mininum_retail_price
     *
     * @param mininumRetailPrice the value for pro_product.mininum_retail_price
     *
     * @mbg.generated
     */
    public void setMininumRetailPrice(BigDecimal mininumRetailPrice) {
        this.mininumRetailPrice = mininumRetailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.warranty_day
     *
     * @return the value of pro_product.warranty_day
     *
     * @mbg.generated
     */
    public String getWarrantyDay() {
        return warrantyDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.warranty_day
     *
     * @param warrantyDay the value for pro_product.warranty_day
     *
     * @mbg.generated
     */
    public void setWarrantyDay(String warrantyDay) {
        this.warrantyDay = warrantyDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.time_unit
     *
     * @return the value of pro_product.time_unit
     *
     * @mbg.generated
     */
    public String getTimeUnit() {
        return timeUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.time_unit
     *
     * @param timeUnit the value for pro_product.time_unit
     *
     * @mbg.generated
     */
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.model
     *
     * @return the value of pro_product.model
     *
     * @mbg.generated
     */
    public String getModel() {
        return model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.model
     *
     * @param model the value for pro_product.model
     *
     * @mbg.generated
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.created_by
     *
     * @return the value of pro_product.created_by
     *
     * @mbg.generated
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.created_by
     *
     * @param createdBy the value for pro_product.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.create_time
     *
     * @return the value of pro_product.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.create_time
     *
     * @param createTime the value for pro_product.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.last_update_by
     *
     * @return the value of pro_product.last_update_by
     *
     * @mbg.generated
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.last_update_by
     *
     * @param lastUpdateBy the value for pro_product.last_update_by
     *
     * @mbg.generated
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.last_update_time
     *
     * @return the value of pro_product.last_update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.last_update_time
     *
     * @param lastUpdateTime the value for pro_product.last_update_time
     *
     * @mbg.generated
     */
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.call_cnt
     *
     * @return the value of pro_product.call_cnt
     *
     * @mbg.generated
     */
    public Integer getCallCnt() {
        return callCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.call_cnt
     *
     * @param callCnt the value for pro_product.call_cnt
     *
     * @mbg.generated
     */
    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.sts_cd
     *
     * @return the value of pro_product.sts_cd
     *
     * @mbg.generated
     */
    public String getStsCd() {
        return stsCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.sts_cd
     *
     * @param stsCd the value for pro_product.sts_cd
     *
     * @mbg.generated
     */
    public void setStsCd(String stsCd) {
        this.stsCd = stsCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.deleted
     *
     * @return the value of pro_product.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.deleted
     *
     * @param deleted the value for pro_product.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_product.warranty
     *
     * @return the value of pro_product.warranty
     *
     * @mbg.generated
     */
    public String getWarranty() {
        return warranty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_product.warranty
     *
     * @param warranty the value for pro_product.warranty
     *
     * @mbg.generated
     */
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
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
        sb.append(", warranty=").append(warranty);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProProduct other = (ProProduct) that;
        return (this.getProId() == null ? other.getProId() == null : this.getProId().equals(other.getProId()))
            && (this.getManId() == null ? other.getManId() == null : this.getManId().equals(other.getManId()))
            && (this.getBrdId() == null ? other.getBrdId() == null : this.getBrdId().equals(other.getBrdId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSkuCd() == null ? other.getSkuCd() == null : this.getSkuCd().equals(other.getSkuCd()))
            && (this.getUpc() == null ? other.getUpc() == null : this.getUpc().equals(other.getUpc()))
            && (this.getEan() == null ? other.getEan() == null : this.getEan().equals(other.getEan()))
            && (this.getRetailPrice() == null ? other.getRetailPrice() == null : this.getRetailPrice().equals(other.getRetailPrice()))
            && (this.getMininumRetailPrice() == null ? other.getMininumRetailPrice() == null : this.getMininumRetailPrice().equals(other.getMininumRetailPrice()))
            && (this.getWarrantyDay() == null ? other.getWarrantyDay() == null : this.getWarrantyDay().equals(other.getWarrantyDay()))
            && (this.getTimeUnit() == null ? other.getTimeUnit() == null : this.getTimeUnit().equals(other.getTimeUnit()))
            && (this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateBy() == null ? other.getLastUpdateBy() == null : this.getLastUpdateBy().equals(other.getLastUpdateBy()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getCallCnt() == null ? other.getCallCnt() == null : this.getCallCnt().equals(other.getCallCnt()))
            && (this.getStsCd() == null ? other.getStsCd() == null : this.getStsCd().equals(other.getStsCd()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getWarranty() == null ? other.getWarranty() == null : this.getWarranty().equals(other.getWarranty()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProId() == null) ? 0 : getProId().hashCode());
        result = prime * result + ((getManId() == null) ? 0 : getManId().hashCode());
        result = prime * result + ((getBrdId() == null) ? 0 : getBrdId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSkuCd() == null) ? 0 : getSkuCd().hashCode());
        result = prime * result + ((getUpc() == null) ? 0 : getUpc().hashCode());
        result = prime * result + ((getEan() == null) ? 0 : getEan().hashCode());
        result = prime * result + ((getRetailPrice() == null) ? 0 : getRetailPrice().hashCode());
        result = prime * result + ((getMininumRetailPrice() == null) ? 0 : getMininumRetailPrice().hashCode());
        result = prime * result + ((getWarrantyDay() == null) ? 0 : getWarrantyDay().hashCode());
        result = prime * result + ((getTimeUnit() == null) ? 0 : getTimeUnit().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateBy() == null) ? 0 : getLastUpdateBy().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getCallCnt() == null) ? 0 : getCallCnt().hashCode());
        result = prime * result + ((getStsCd() == null) ? 0 : getStsCd().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getWarranty() == null) ? 0 : getWarranty().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table pro_product
     *
     * @mbg.generated
     */
    public enum Column {
        proId("pro_id", "proId", "INTEGER", false),
        manId("man_id", "manId", "INTEGER", false),
        brdId("brd_id", "brdId", "INTEGER", false),
        title("title", "title", "VARCHAR", false),
        skuCd("sku_cd", "skuCd", "VARCHAR", false),
        upc("upc", "upc", "VARCHAR", false),
        ean("ean", "ean", "VARCHAR", false),
        retailPrice("retail_price", "retailPrice", "DECIMAL", false),
        mininumRetailPrice("mininum_retail_price", "mininumRetailPrice", "DECIMAL", false),
        warrantyDay("warranty_day", "warrantyDay", "VARCHAR", false),
        timeUnit("time_unit", "timeUnit", "VARCHAR", false),
        model("model", "model", "VARCHAR", false),
        createdBy("created_by", "createdBy", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        lastUpdateBy("last_update_by", "lastUpdateBy", "VARCHAR", false),
        lastUpdateTime("last_update_time", "lastUpdateTime", "TIMESTAMP", false),
        callCnt("call_cnt", "callCnt", "INTEGER", false),
        stsCd("sts_cd", "stsCd", "CHAR", false),
        deleted("deleted", "deleted", "BIT", false),
        warranty("warranty", "warranty", "LONGVARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table pro_product
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}