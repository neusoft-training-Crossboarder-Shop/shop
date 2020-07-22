package neu.train.project.wallet.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import neu.train.framework.web.domain.BaseEntity;
import neu.train.project.validate.DeleteGroup;
import neu.train.project.validate.InsertGroup;
import neu.train.project.validate.UpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class WaaWalletAccount extends BaseEntity {

    private Integer buyerId;

    @NotNull(message = "accountName can not be null",groups = {InsertGroup.class})
    private String accountName;

    @NotNull(message = "email can not be null",groups = {InsertGroup.class})
    private String email;

    @NotNull(message = "password can not be null",groups = {InsertGroup.class})
    private String password;

    private Date activeTime;

    private String isActive;

    private Byte status;

    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer accountType;

    private String holdOrderTime;

    private String autoPayStatus;

    public WaaWalletAccount(Integer buyerId, String accountName, String email, String password, Date activeTime, String isActive, Byte status, String createdBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer accountType, String holdOrderTime, String autoPayStatus) {
        this.buyerId = buyerId;
        this.accountName = accountName;
        this.email = email;
        this.password = password;
        this.activeTime = activeTime;
        this.isActive = isActive;
        this.status = status;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.accountType = accountType;
        this.holdOrderTime = holdOrderTime;
        this.autoPayStatus = autoPayStatus;
    }

    public WaaWalletAccount() {
        super();
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    @Email(message = "邮箱格式不正确")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getHoldOrderTime() {
        return holdOrderTime;
    }

    public void setHoldOrderTime(String holdOrderTime) {
        this.holdOrderTime = holdOrderTime == null ? null : holdOrderTime.trim();
    }

    public String getAutoPayStatus() {
        return autoPayStatus;
    }

    public void setAutoPayStatus(String autoPayStatus) {
        this.autoPayStatus = autoPayStatus == null ? null : autoPayStatus.trim();
    }
}