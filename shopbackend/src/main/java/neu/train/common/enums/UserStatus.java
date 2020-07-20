package neu.train.common.enums;

/**
 * User Status
 * 
 * @author
 */
public enum UserStatus
{
    OK("0", "Normal"), DISABLE("1", "Stopped"), DELETED("2", "Delete");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
