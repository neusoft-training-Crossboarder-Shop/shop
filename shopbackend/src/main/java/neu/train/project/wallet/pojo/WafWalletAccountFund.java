package neu.train.project.wallet.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.Serializers;
import neu.train.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class WafWalletAccountFund extends BaseEntity {
    private Integer buyerId;

    private BigDecimal availableMoney;

    private BigDecimal depositingMoney;

    private BigDecimal withdrawingMoney;

    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private String currency;

    private Integer version;

    public WafWalletAccountFund(Integer buyerId, BigDecimal availableMoney, BigDecimal depositingMoney, BigDecimal withdrawingMoney, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, String currency, Integer version) {
        this.buyerId = buyerId;
        this.availableMoney = availableMoney;
        this.depositingMoney = depositingMoney;
        this.withdrawingMoney = withdrawingMoney;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.currency = currency;
        this.version = version;
    }

    public WafWalletAccountFund() {
        super();
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public BigDecimal getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(BigDecimal availableMoney) {
        this.availableMoney = availableMoney;
    }

    public BigDecimal getDepositingMoney() {
        return depositingMoney;
    }

    public void setDepositingMoney(BigDecimal depositingMoney) {
        this.depositingMoney = depositingMoney;
    }

    public BigDecimal getWithdrawingMoney() {
        return withdrawingMoney;
    }

    public void setWithdrawingMoney(BigDecimal withdrawingMoney) {
        this.withdrawingMoney = withdrawingMoney;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}