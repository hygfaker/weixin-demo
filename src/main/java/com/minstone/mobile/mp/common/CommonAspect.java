package com.minstone.mobile.mp.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangyg on 2017/8/10.
 */
@Aspect
@Component
public class CommonAspect {
    private static Logger logger = LoggerFactory.getLogger(CommonAspect.class);

    private String echostr;

//    @Pointcut("execution(public * com.minstone.mobile.mp.common.contoller..*(..))")
    @Pointcut("execution(public * com.minstone.mobile.mp.wechat..*.*(..))")

    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("============================ WeiXin ============================");

        logger.info("执行的方法：{}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        logger.info("调用的API：{}",request.getRequestURI());

        logger.info("请求方法：{}",request.getMethod());

        logger.info("ip地址为：{}",request.getRemoteHost(),request.getRemoteAddr(),request.getRequestURL());

        logger.info("传递的参数：{}",request.getQueryString());

        Class[] parameterTypes = ( (MethodSignature)joinPoint.getSignature() ).getMethod().getParameterTypes() ;


        switch( request.getMethod() ){
            case "GET" :{
                logger.info("传递的参数：{}",request.getServletPath() ) ;
                break ;
            }
            case "POST" :{
                Object reqDto = joinPoint.getArgs()[0] ;
                if( reqDto != null ){
                    logger.info("传递的参数：{}",reqDto.toString()) ;
                }
                break ;
            }
            default : break ;
        }

        logger.info("================================================================");

    }
}
