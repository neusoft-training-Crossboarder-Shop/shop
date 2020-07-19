package neu.train.project.order.vo;

import java.math.BigDecimal;

public class GetShelfPriceAmount {
    private Integer dilId;
    private BigDecimal salPrice;
    private Integer shelfStockAmount;

    public GetShelfPriceAmount(Integer dilId, BigDecimal salPrice, Integer shelfStockAmount) {
        this.dilId = dilId;
        this.salPrice = salPrice;
        this.shelfStockAmount = shelfStockAmount;
    }

    public Integer getDilId() {
        return dilId;
    }

    public void setDilId(Integer dilId) {
        this.dilId = dilId;
    }

    public BigDecimal getSalPrice() {
        return salPrice;
    }

    public void setSalPrice(BigDecimal salPrice) {
        this.salPrice = salPrice;
    }

    public Integer getShelfStockAmount() {
        return shelfStockAmount;
    }

    public void setShelfStockAmount(Integer shelfStockAmount) {
        this.shelfStockAmount = shelfStockAmount;
    }
}
