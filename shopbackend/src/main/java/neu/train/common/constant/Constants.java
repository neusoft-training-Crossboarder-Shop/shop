package neu.train.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 * 
 * @author
 */
public class Constants
{
    /**
     * UTF-8 Code集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK Code集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用SuccessSignal
     */
    public static final String SUCCESS = "0";

    /**
     * 通用FailSignal
     */
    public static final String FAIL = "1";

    /**
     * 登录Success
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录Fail
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * Validation Code  redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录User  redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * Validation Code 有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * User ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * User Name
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /**
     * User 头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     *  Create   time
     */
    public static final String JWT_CREATED = "created";

    /**
     * User Authority
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * Dict   管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
