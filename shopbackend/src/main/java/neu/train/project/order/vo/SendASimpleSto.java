package neu.train.project.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SendASimpleSto {
    private Integer stoId;

    private Integer qty;

    private BigDecimal purchasePrice;

    private String paidTime;

    private Integer orderStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date lastUpdateTime;

    private SimpleStr store;

    private SimplePro product;


    public SendASimpleSto() {

    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public SimplePro getProduct() {
        return product;
    }

    public void setProduct(SimplePro product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public SimpleStr getStore() {
        return store;
    }

    public void setStore(SimpleStr store) {
        this.store = store;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "SendASimpleSto{" +
                "stoId=" + stoId +
                ", qty=" + qty +
                ", purchasePrice=" + purchasePrice +
                ", paidTime='" + paidTime + '\'' +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", store=" + store +
                ", product=" + product +
                '}';
    }
}
