package neu.train.framework.security;

/**
 * User 登录对象
 * 
 * @author
 */
public class LoginBody
{
    /**
     * User 名
     */
    private String username;

    /**
     * User 密码
     */
    private String password;

    /**
     * Validation Code
     */
    private String code;

    /**
     * 唯一Signal
     */
    private String uuid = "";

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
