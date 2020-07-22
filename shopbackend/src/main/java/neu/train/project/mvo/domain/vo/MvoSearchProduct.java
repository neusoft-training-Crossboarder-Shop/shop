package neu.train.project.mvo.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

public class MvoSearchProduct {
    private Integer manId;
    private Integer proId;
    private String categoryName;
    private String title;
    private String brandName;
    private BigDecimal topPrice;
    private BigDecimal lowPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** Start  time */
    @JsonIgnore
    private String beginTime;
    @JsonIgnore
    private String endTime;


    public MvoSearchProduct(Integer manId, Integer proId, String categoryName, String title, String brandName, BigDecimal topPrice, BigDecimal lowPrice, String beginTime, String endTime) {
        this.manId = manId;
        this.proId = proId;
        this.categoryName = categoryName;
        this.title = title;
        this.brandName = brandName;
        this.topPrice = topPrice;
        this.lowPrice = lowPrice;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getManId() {
        return manId;
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

    public void setManId(Integer manId) {
        this.manId = manId;
    }
}
