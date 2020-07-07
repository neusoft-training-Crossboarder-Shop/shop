package neu.train.project.wallet.vo;

import neu.train.project.validate.InsertGroup;

import javax.validation.constraints.NotNull;

public class GetANewWallet {
    @NotNull(message = "accountName can not be null",groups = {InsertGroup.class})
    private String accountName;

    @NotNull(message = "email can not be null",groups = {InsertGroup.class})
    private String email;

    @NotNull(message = "password can not be null",groups = {InsertGroup.class})
    private String password;

    @NotNull(message = "currency can not be null",groups = {InsertGroup.class})
    private String currency;

    public GetANewWallet(@NotNull(message = "accountName can not be null", groups = {InsertGroup.class}) String accountName, @NotNull(message = "email can not be null", groups = {InsertGroup.class}) String email, @NotNull(message = "password can not be null", groups = {InsertGroup.class}) String password, @NotNull(message = "currency can not be null", groups = {InsertGroup.class}) String currency) {
        this.accountName = accountName;
        this.email = email;
        this.password = password;
        this.currency = currency;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
