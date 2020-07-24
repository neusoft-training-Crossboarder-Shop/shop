package neu.train.project.order.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import neu.train.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ShaShippingAddress extends BaseEntity {
    private Integer shaId;

    private Integer stoId;

    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String lastUpdateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    private Integer callCnt;

    private String stsCd;

    private String familyName;

    private String givenName;

    private String countryName;

    private String countryIsoCd;

    private String stateOrProvinceName;

    private String stateOrProvinceCd;

    private String cityName;

    private String addressLine1;

    private String postalCd;

    private String contactPhoneNo;

    private String email;

    private BigDecimal freightCost;

    private String carrierName;

    public ShaShippingAddress(Integer shaId, Integer stoId, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer callCnt, String stsCd, String familyName, String givenName, String countryName, String countryIsoCd, String stateOrProvinceName, String stateOrProvinceCd, String cityName, String addressLine1, String postalCd, String contactPhoneNo, String email, BigDecimal freightCost, String carrierName) {
        this.shaId = shaId;
        this.stoId = stoId;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.callCnt = callCnt;
        this.stsCd = stsCd;
        this.familyName = familyName;
        this.givenName = givenName;
        this.countryName = countryName;
        this.countryIsoCd = countryIsoCd;
        this.stateOrProvinceName = stateOrProvinceName;
        this.stateOrProvinceCd = stateOrProvinceCd;
        this.cityName = cityName;
        this.addressLine1 = addressLine1;
        this.postalCd = postalCd;
        this.contactPhoneNo = contactPhoneNo;
        this.email = email;
        this.freightCost = freightCost;
        this.carrierName = carrierName;
    }

    public ShaShippingAddress() {
        super();
    }

    public Integer getShaId() {
        return shaId;
    }

    public void setShaId(Integer shaId) {
        this.shaId = shaId;
    }

    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName == null ? null : familyName.trim();
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName == null ? null : givenName.trim();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public String getCountryIsoCd() {
        return countryIsoCd;
    }

    public void setCountryIsoCd(String countryIsoCd) {
        this.countryIsoCd = countryIsoCd == null ? null : countryIsoCd.trim();
    }

    public String getStateOrProvinceName() {
        return stateOrProvinceName;
    }

    public void setStateOrProvinceName(String stateOrProvinceName) {
        this.stateOrProvinceName = stateOrProvinceName == null ? null : stateOrProvinceName.trim();
    }

    public String getStateOrProvinceCd() {
        return stateOrProvinceCd;
    }

    public void setStateOrProvinceCd(String stateOrProvinceCd) {
        this.stateOrProvinceCd = stateOrProvinceCd == null ? null : stateOrProvinceCd.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1 == null ? null : addressLine1.trim();
    }

    public String getPostalCd() {
        return postalCd;
    }

    public void setPostalCd(String postalCd) {
        this.postalCd = postalCd == null ? null : postalCd.trim();
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo == null ? null : contactPhoneNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public BigDecimal getFreightCost() {
        return freightCost;
    }

    public void setFreightCost(BigDecimal freightCost) {
        this.freightCost = freightCost;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName == null ? null : carrierName.trim();
    }
}