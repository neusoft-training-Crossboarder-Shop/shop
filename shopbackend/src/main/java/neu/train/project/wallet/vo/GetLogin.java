package neu.train.project.wallet.vo;

import neu.train.project.validate.SelectGroup;

import javax.validation.constraints.NotNull;

public class GetLogin {

    @NotNull(message = "accountName can not be null",groups = {SelectGroup.class})
    private String accountName;

    @NotNull(message = "password can not be null",groups = {SelectGroup.class})
    private String password;

    public GetLogin(@NotNull(message = "accountName can not be null", groups = {SelectGroup.class}) String accountName, @NotNull(message = "password can not be null", groups = {SelectGroup.class}) String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
