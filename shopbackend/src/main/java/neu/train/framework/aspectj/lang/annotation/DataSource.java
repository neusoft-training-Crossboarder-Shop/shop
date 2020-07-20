package neu.train.framework.aspectj.lang.annotation;

import neu.train.framework.aspectj.lang.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * 自定义多Data 源切换注解
 *
 * 优先级：先方法，后类，如果方法覆盖了类上的Data 源Type ，以方法的为准，否则以类上的为准
 *
 * @author
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource
{
    /**
     * 切换Data 源Name
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
