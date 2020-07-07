package neu.train.project.bvo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import neu.train.project.bvo.domain.productDomain.Manufacturer;
import neu.train.project.bvo.domain.productDomain.ProductBrand;
import neu.train.project.bvo.domain.productDomain.ProductCategory;
import neu.train.project.bvo.domain.productDomain.ProductImage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(value=("handler"))
public class SearchProduct implements Serializable {
    private Integer proId;
    private Integer manId;
    private Integer brdId;
    private String title;
    private BigDecimal retailPrice;
    private Manufacturer manufacturer;
    private ProductBrand productBrand;
    private List<ProductImage> imageList;
    private List<ProductCategory> categories;

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

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
    }

    public List<ProductImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<ProductImage> imageList) {
        this.imageList = imageList;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }
}
