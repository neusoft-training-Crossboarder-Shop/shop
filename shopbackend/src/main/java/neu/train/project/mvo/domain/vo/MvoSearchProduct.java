package neu.train.project.mvo.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MvoSearchProduct {
    private Integer proId;
    private String categoryName;
    private String title;
    private String brandName;
    private BigDecimal topPrice;
    private BigDecimal lowPrice;
    private Date beginTime;
    private Date endTime;

    public MvoSearchProduct(Integer proId, String categoryName, String title, String brandName, BigDecimal topPrice, BigDecimal lowPrice, Date beginTime, Date endTime) {
        this.proId = proId;
        this.categoryName = categoryName;
        this.title = title;
        this.brandName = brandName;
        this.topPrice = topPrice;
        this.lowPrice = lowPrice;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public BigDecimal getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(BigDecimal topPrice) {
        this.topPrice = topPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
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

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
