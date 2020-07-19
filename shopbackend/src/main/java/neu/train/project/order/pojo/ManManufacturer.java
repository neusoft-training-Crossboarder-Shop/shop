package neu.train.project.order.pojo;

import java.util.Date;

public class ManManufacturer {
    private Integer manId;

    private Integer sysUserId;

    private String nameEn;

    private String nameCn;

    private String gmcReportType;

    private String gmcReportUrl;

    private String createdBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    private Boolean deleted;

    private String manDesc;

    private String picUrl;

    private String description;

    public ManManufacturer(Integer manId, Integer sysUserId, String nameEn, String nameCn, String gmcReportType, String gmcReportUrl, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd, Boolean deleted, String manDesc, String picUrl, String description) {
        this.manId = manId;
        this.sysUserId = sysUserId;
        this.nameEn = nameEn;
        this.nameCn = nameCn;
        this.gmcReportType = gmcReportType;
        this.gmcReportUrl = gmcReportUrl;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
        this.deleted = deleted;
        this.manDesc = manDesc;
        this.picUrl = picUrl;
        this.description = description;
    }

    public ManManufacturer() {
        super();
    }

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    public String getGmcReportType() {
        return gmcReportType;
    }

    public void setGmcReportType(String gmcReportType) {
        this.gmcReportType = gmcReportType == null ? null : gmcReportType.trim();
    }

    public String getGmcReportUrl() {
        return gmcReportUrl;
    }

    public void setGmcReportUrl(String gmcReportUrl) {
        this.gmcReportUrl = gmcReportUrl == null ? null : gmcReportUrl.trim();
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getManDesc() {
        return manDesc;
    }

    public void setManDesc(String manDesc) {
        this.manDesc = manDesc == null ? null : manDesc.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}