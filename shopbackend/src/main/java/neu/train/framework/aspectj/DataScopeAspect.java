package neu.train.framework.aspectj;

import neu.train.common.utils.ServletUtils;
import neu.train.common.utils.StringUtils;
import neu.train.common.utils.spring.SpringUtils;
import neu.train.framework.aspectj.lang.annotation.DataScope;
import neu.train.framework.security.LoginUser;
import neu.train.framework.security.service.TokenService;
import neu.train.framework.web.domain.BaseEntity;
import neu.train.project.system.domain.SysRole;
import neu.train.project.system.domain.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Data 过滤处理
 * 
 * @author
 */
@Aspect
@Component
public class DataScopeAspect
{
    /**
     * 全部 Data Authority
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定 Data Authority
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门 Data Authority
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下 Data Authority
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人 Data Authority
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     *  Data Authority过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    // 配置织入点
    @Pointcut("@annotation(neu.train.framework.aspectj.lang.annotation.DataScope)")
    public void dataScopePointCut()
    {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint)
    {
        // 获得注解
        DataScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null)
        {
            return;
        }
        // 获取当前的User
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        SysUser currentUser = loginUser.getUser();
        if (currentUser != null)
        {
            // 如果是超级管理员，则不过滤Data
            if (!currentUser.isAdmin())
            {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(),
                        controllerDataScope.userAlias());
            }
        }
    }

    /**
     * Data 范围过滤
     * 
     * @param joinPoint 切点
     * @param user User
     */
    public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String deptAlias, String userAlias)
    {
        StringBuilder sqlString = new StringBuilder();

        for (SysRole role : user.getRoles())
        {
            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope))
            {
                sqlString = new StringBuilder();
                break;
            }
            else if (DATA_SCOPE_CUSTOM.equals(dataScope))
            {
                sqlString.append(StringUtils.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", deptAlias,
                        role.getRoleId()));
            }
            else if (DATA_SCOPE_DEPT.equals(dataScope))
            {
                sqlString.append(StringUtils.format(" OR {}.dept_id = {} ", deptAlias, user.getDeptId()));
            }
            else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope))
            {
                sqlString.append(StringUtils.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )",
                        deptAlias, user.getDeptId(), user.getDeptId()));
            }
            else if (DATA_SCOPE_SELF.equals(dataScope))
            {
                if (StringUtils.isNotBlank(userAlias))
                {
                    sqlString.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, user.getUserId()));
                }
                else
                {
                    //  Data Authority为仅本人且没有userAlias别名不查询任何Data
                    sqlString.append(" OR 1=0 ");
                }
            }
        }

        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            BaseEntity baseEntity = (BaseEntity) joinPoint.getArgs()[0];
            baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }
}
