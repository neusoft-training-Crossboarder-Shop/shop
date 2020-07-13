package neu.train.project.order.vo;

import neu.train.project.order.pojo.ProProduct;
import neu.train.project.order.pojo.StoStoreOrder;
import neu.train.project.order.pojo.StrStore;

import java.math.BigDecimal;
import java.util.Date;

public class SendAStoOrder {
    private Integer stoId;

    private ProProduct product;

    private Integer qty;

    private StrStore store;

    private BigDecimal purchasePrice;

    private String paidTime;

    private Integer orderStatus;

    private Date createTime;

    private String createdBy;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    public SendAStoOrder(StoStoreOrder stoStoreOrder,StrStore strStore,ProProduct proProduct){
        this.stoId = stoStoreOrder.getStoId();
        this.product = proProduct;
        this.qty = stoStoreOrder.getQty();
        this.store = strStore;
        this.purchasePrice = stoStoreOrder.getPurchasePrice();
        this.paidTime = stoStoreOrder.getPaidTime();
        this.orderStatus = stoStoreOrder.getOrderStatus();
        this.createTime = stoStoreOrder.getCreateTime();
        this.createdBy = stoStoreOrder.getCreatedBy();
        this.lastUpdateBy = stoStoreOrder.getLastUpdateBy();
        this.lastUpdateTime = stoStoreOrder.getLastUpdateTime();
        this.callCnt = stoStoreOrder.getCallCnt();
        this.stsCd = stoStoreOrder.getStsCd();
    }

    public SendAStoOrder(Integer stoId, ProProduct product, Integer qty, StrStore store, BigDecimal purchasePrice, String paidTime, Integer orderStatus, Date createTime, String createdBy, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd) {
        this.stoId = stoId;
        this.product = product;
        this.qty = qty;
        this.store = store;
        this.purchasePrice = purchasePrice;
        this.paidTime = paidTime;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public ProProduct getProduct() {
        return product;
    }

    public void setProduct(ProProduct product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public StrStore getStore() {
        return store;
    }

    public void setStore(StrStore store) {
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
}
