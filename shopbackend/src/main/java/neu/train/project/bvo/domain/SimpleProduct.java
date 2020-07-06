package neu.train.project.bvo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class SimpleProduct implements Serializable {
    private Integer proId;
    private String categoryName;
    private String title;
    private String manufacturerName;
    private String brandName;
    private BigDecimal price;
    private BigDecimal lowPrice;
    private BigDecimal topPrice;
    private String imageUrl;

    public SimpleProduct(){

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

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(BigDecimal topPrice) {
        this.topPrice = topPrice;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
