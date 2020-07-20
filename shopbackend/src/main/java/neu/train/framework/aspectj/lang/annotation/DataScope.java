package neu.train.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

/**
 *  Data Authority过滤注解
 * 
 * @author
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * User 表的别名
     */
    public String userAlias() default "";
}
