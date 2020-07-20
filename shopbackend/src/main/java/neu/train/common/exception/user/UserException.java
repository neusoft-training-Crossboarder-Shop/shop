package neu.train.common.exception.user;

import neu.train.common.exception.BaseException;

/**
 * User 信息异常类
 * 
 * @author
 */
class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
