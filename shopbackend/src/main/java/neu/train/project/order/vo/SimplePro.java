package neu.train.project.order.vo;

import java.math.BigDecimal;


public class SimplePro {

    private Integer proId;

    private String title;

    private BigDecimal retailPrice;


    public SimplePro() {

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

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }
}
