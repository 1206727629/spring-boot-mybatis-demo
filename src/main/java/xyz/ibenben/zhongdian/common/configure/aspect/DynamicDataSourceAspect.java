package xyz.ibenben.zhongdian.common.configure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.ibenben.zhongdian.common.configure.DynamicDataSourceContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ywt start
 * @create 2022-06-11 13:34
 */
@Aspect
@Order(-1)//Order中的数字代表启动优先级,-1是比0还有更高的优先级
@Component
public class DynamicDataSourceAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    //@annotation用于匹配当前执行方法持有指定注解的方法
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void daoAspect(){ }


    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ds = request.getHeader("ds");
        if(ds != null){
            DynamicDataSourceContextHolder.useSlaveDataSource(ds);
        }else{
            DynamicDataSourceContextHolder.useMasterDataSource();
        }
    }
}

