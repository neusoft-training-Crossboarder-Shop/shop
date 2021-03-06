package neu.train.common.exception.user;

/**
 * Validation Code 失效异常类
 * 
 * @author
 */
public class CaptchaExpireException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException()
    {
        super("user.jcaptcha.expire", null);
    }
}
