package neu.train.project.order.vo;

import neu.train.project.order.pojo.ProProduct;
import neu.train.project.order.pojo.SaoSalesOrder;
import neu.train.project.order.pojo.StrStore;

import java.math.BigDecimal;
import java.util.Date;

public class SendComplexSao {

    private Integer salesOderId;

    private Integer manId;

    private Integer qty;

    private BigDecimal price;

    private Integer stoId;

    private BigDecimal purchasePrice;

    private String trackingNo;

    private String createdBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    private SimpleStr store;

    private SimplePro product;

    public SendComplexSao(){

    }

    public SendComplexSao(SaoSalesOrder saoSalesOrder, ProProduct proProduct, StrStore strStore){
        this.salesOderId=saoSalesOrder.getSalesOderId();
        this.manId=saoSalesOrder.getManId();
        this.qty=saoSalesOrder.getQty();
        this.price=saoSalesOrder.getPrice();
        this.stoId=saoSalesOrder.getStoId();
        this.purchasePrice=saoSalesOrder.getPurchasePrice();
        this.trackingNo=saoSalesOrder.getTrackingNo();
        this.createdBy=saoSalesOrder.getCreatedBy();
        this.createTime=saoSalesOrder.getCreateTime();
        this.lastUpdateBy=saoSalesOrder.getLastUpdateBy();
        this.lastUpdateTime=saoSalesOrder.getLastUpdateTime();
        this.callCnt=saoSalesOrder.getCallCnt();
        this.stsCd=saoSalesOrder.getStsCd();
        this.product=new SimplePro();
        this.product.setProId(proProduct.getProId());
        this.product.setRetailPrice(proProduct.getRetailPrice());
        this.product.setTitle(proProduct.getTitle());
        this.store=new SimpleStr();
        this.store.setStoreId(strStore.getStoreId());
        this.store.setPlatformType(strStore.getStoreId());
        this.store.setStoreName(strStore.getStoreName());
    }



    public SendComplexSao(Integer salesOderId, Integer manId, Integer qty, BigDecimal price, Integer stoId, BigDecimal purchasePrice, String trackingNo, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd, SimpleStr store, SimplePro product) {
        this.salesOderId = salesOderId;
        this.manId = manId;
        this.qty = qty;
        this.price = price;
        this.stoId = stoId;
        this.purchasePrice = purchasePrice;
        this.trackingNo = trackingNo;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
        this.store = store;
        this.product = product;
    }

    public Integer getSalesOderId() {
        return salesOderId;
    }

    public void setSalesOderId(Integer salesOderId) {
        this.salesOderId = salesOderId;
    }

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
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

    public SimpleStr getStore() {
        return store;
    }

    public void setStore(SimpleStr store) {
        this.store = store;
    }

    public SimplePro getProduct() {
        return product;
    }

    public void setProduct(SimplePro product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "SendComplexSao{" +
                "salesOderId=" + salesOderId +
                ", manId=" + manId +
                ", qty=" + qty +
                ", price=" + price +
                ", stoId=" + stoId +
                ", purchasePrice=" + purchasePrice +
                ", trackingNo='" + trackingNo + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", callCnt=" + callCnt +
                ", stsCd='" + stsCd + '\'' +
                ", store=" + store +
                ", product=" + product +
                '}';
    }
}
