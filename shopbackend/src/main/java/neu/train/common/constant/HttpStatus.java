package neu.train.common.constant;

/**
 * The Status of Http Response
 */

public class HttpStatus
{
    /**
     * OperationSuccess
     */
    public static final int SUCCESS = 200;

    /**
     * 对象 Create   Success
     */
    public static final int CREATED = 201;

    /**
     * 请求Already经 be 接受
     */
    public static final int ACCEPTED = 202;

    /**
     * OperationAlready经执行Success，但是没有返回Data
     */
    public static final int NO_CONTENT = 204;

    /**
     * 资源Already be 移除
     */
    public static final int MOVED_PERM = 301;

    /**
     * 重定向
     */
    public static final int SEE_OTHER = 303;

    /**
     * 资源没有 be  Modify
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    public static final int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 405;

    /**
     * 资源冲突，或者资源 be 锁
     */
    public static final int CONFLICT = 409;

    /**
     * 不支持的Data ，媒体Type
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED = 501;

    public static final int INSERT_SUCCESS = 211;
    public static final int UPDATE_SUCCESS = 212;
    public static final int DELETE_SUCCESS = 213;
}
