package neu.train.project.order.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import neu.train.framework.web.domain.BaseEntity;

import java.util.Date;

public class StrStore extends BaseEntity {
    private Integer storeId;

    private Integer dsrId;

    private Integer platformType;

    private String storeName;

    private String authencationCode;

    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String lastUpdateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    public StrStore(Integer storeId, Integer dsrId, Integer platformType, String storeName, String authencationCode, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd) {
        this.storeId = storeId;
        this.dsrId = dsrId;
        this.platformType = platformType;
        this.storeName = storeName;
        this.authencationCode = authencationCode;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
    }

    public StrStore() {
        super();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getDsrId() {
        return dsrId;
    }

    public void setDsrId(Integer dsrId) {
        this.dsrId = dsrId;
    }

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getAuthencationCode() {
        return authencationCode;
    }

    public void setAuthencationCode(String authencationCode) {
        this.authencationCode = authencationCode == null ? null : authencationCode.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
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
        this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
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
        this.stsCd = stsCd == null ? null : stsCd.trim();
    }
}