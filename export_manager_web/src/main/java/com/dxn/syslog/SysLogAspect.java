package com.dxn.syslog;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dxn.domain.system.SysLog;
import com.dxn.domain.system.User;
import com.dxn.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;



/**
 * @author 29237
 */
@Component
@Aspect
public class SysLogAspect {

    @Reference
    private SysLogService sysLogService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 保存日志：
     * 1、获取数据
     * 2、创建SysLog对象，并封装数据
     * 3、保存SysLog对象
     *
     * @param pjp
     * @return
     */
    /**
     * 配置切入点
     * @param pjp
     * @return
     */
    @Around("execution(* com.dxn.controller.*.*.*(..))")
    public Object writeLog(ProceedingJoinPoint pjp) { // pjp ：代表目标方法对象
        Object o = null;
        try {
            //调用目标方法  返回值是目标方法的返回值
            o = pjp.proceed();
            //得到目标方法的签名
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            //方法对象
            Method method = signature.getMethod();
            //判断当前方法上是否有指定注解
            if(method.isAnnotationPresent(RequestMapping.class)){
                //得到注解
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                // 1、获取数据
                User loginUser = (User) session.getAttribute("user");
                // 2、创建SysLog对象，并封装数据
                SysLog sysLog = new SysLog();
                sysLog.setUserName(loginUser.getUserName());
                sysLog.setCompanyId(loginUser.getCompanyId());
                sysLog.setCompanyName(loginUser.getCompanyName());
                sysLog.setTime(new Date());
                //获取客户机ip地址
                sysLog.setIp(request.getRemoteAddr());
                //得到方法名称
                sysLog.setMethod(method.getName());
                //得到注解的name属性值
                sysLog.setAction(annotation.name());
                // 3、保存SysLog对象
                sysLogService.save(sysLog);
            }


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return o;
    }
}
