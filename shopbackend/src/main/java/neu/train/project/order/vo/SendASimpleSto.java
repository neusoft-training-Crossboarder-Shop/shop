package neu.train.project.order.vo;

import java.math.BigDecimal;
import java.util.Date;

public class SendASimpleSto {
    private Integer stoId;

    private SimplePro simplePro;

    private Integer qty;

    private SimpleStr simpleStr;

    private BigDecimal purchasePrice;

    private String paidTime;

    private Integer orderStatus;

    private Date createTime;

    private Date lastUpdateTime;

    public SendASimpleSto(Integer stoId, SimplePro simplePro, Integer qty, SimpleStr simpleStr, BigDecimal purchasePrice, String paidTime, Integer orderStatus, Date createTime, Date lastUpdateTime) {
        this.stoId = stoId;
        this.simplePro = simplePro;
        this.qty = qty;
        this.simpleStr = simpleStr;
        this.purchasePrice = purchasePrice;
        this.paidTime = paidTime;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public SimplePro getSimplePro() {
        return simplePro;
    }

    public void setSimplePro(SimplePro simplePro) {
        this.simplePro = simplePro;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public SimpleStr getSimpleStr() {
        return simpleStr;
    }

    public void setSimpleStr(SimpleStr simpleStr) {
        this.simpleStr = simpleStr;
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
}
