package com.minstone.mobile.mp.common;
import com.minstone.mobile.mp.common.constants.CommonResultEnum;
import com.minstone.mobile.mp.utils.JsonUtil;
import com.minstone.mobile.mp.utils.ReflectBeanUtil;
import com.minstone.mobile.mp.wechat.publics.domain.WxPublic;
import com.minstone.mobile.mp.wechat.publics.service.IWxPublicService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by huangyg on 2017/8/10.
 */
@Aspect
@Component
public class CommonAspect {


    @Autowired
    private WxMpService wxService;

    @Autowired
    private IWxPublicService publicService;

    private static Logger logger = LoggerFactory.getLogger(CommonAspect.class);

    @Pointcut("execution(public * com.minstone.mobile.mp.wechat..*.controller..*(..))")
    public void pointCut(){}


    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws WxErrorException, IOException {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("============================ WeiXin ============================");
        logger.info("URL           :{}   {}",request.getMethod(),request.getRequestURI());
        logger.info("Method        :{}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("Address       :{}",request.getRemoteHost(),request.getRemoteAddr(),request.getRequestURL());
        Class[] parameterTypes = ( (MethodSignature)joinPoint.getSignature() ).getMethod().getParameterTypes() ;
        String publicCode = null;
        switch( request.getMethod() ){
            case "GET" :{
                logger.info("Parameter     :{}",request.getQueryString()) ;
                break ;
            }
            case "POST" :{
                Object reqDto = joinPoint.getArgs()[0] ;
                if( reqDto != null ){
                    try {
                        // 适配 post 请求content-type 为 application/json
                        Map map = ReflectBeanUtil.objectToMap(reqDto);
                        if (map.get("publicCode") != null){
                            publicCode = (String) map.get("publicCode");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new CommonException(CommonResultEnum.SERVER_ERROR);
                    }
                    // 判断 joinPoint
                    logger.info("Parameter     :{}", JsonUtil.toJson(reqDto)) ;
                }
                break ;
            }
            default : break ;
        }

        // 一下情况不需要验证 publicCode
        // 公众号管理添加公众号、模板消息发送模板消息，上传图文永久素材（@requestBody 参数映射后识别不了 publicCode，模板消息实体中没有 publicCode）
        if (request.getRequestURL().lastIndexOf("/public/add") < 0){
            // 每次都拦截参数 publicCode
            if (publicCode == null ){
                publicCode = request.getParameter("publicCode");
            }
            if (publicCode == null){
                // 判断
                throw  new CommonException(CommonResultEnum.PARAMS_PUBLICCODE_MISSING);
            }
            WxPublic checkPublic = publicService.get(publicCode);
            if (checkPublic == null) {
                throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
            }
            // 判断是否需要切换公众号
            if (!checkPublic.getAppSecret().equals(new WxMpInMemoryConfigStorage().getSecret())) {
                WxMpInMemoryConfigStorage config = publicService.switchPublic(checkPublic);
                wxService.setWxMpConfigStorage(config);
            }
        }


    }

/*
* 由于支持多公众号的切换，所以要求每调用接口都需要传递 publicCode，这里用 aop 获取 publicCode 属性
* JoinPoint 对象能够获取 Controller 中 params 的参数
* request 对象能够获取用户传递的 publicCode 参数，
* 这里需要注意的是：request 只能获取 content-type 为：form-data 和 x-www-form-urlencoded。如果 post 请求的 content-type 为 application/json。则要求：
* 1. 使用 JoinPoint 的 getArgs()方法，来获取 publicCode 参数。
* 2. Controller 的形参 bean 中有 publicCode 属性。
*
* */


//    @Around("pointCut()")
//    public void storageConfig(JoinPoint joinPoint) throws WxErrorException, IOException {
//
//        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest() ;
//        Object returnObject = null ;
//
//        Object target = joinPoint.getTarget() ;
//        String methodName = joinPoint.getSignature().getName() ;
//        Class[] parameterTypes = ( (MethodSignature)joinPoint.getSignature() ).getMethod().getParameterTypes() ;
//        Method method = null ;
//        try {
//            method = target.getClass().getMethod( methodName , parameterTypes ) ;
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        // 判断 method 是否包含 StorageAnnotation 注解
//        if (method != null && method.isAnnotationPresent(StorageAnnotation.class)){
//            WxPublic checkPublic = publicService.get(req.getParameter("publicCode"));
//            if (checkPublic == null) {
//                throw new CommonException(CommonResultEnum.PUBLIC_NOTFOUND);
//            }
//            // 判断是否需要切换公众号
//            if (!checkPublic.getAppSerct().equals(new WxMpInMemoryConfigStorage().getSecret())) {
//                WxMpInMemoryConfigStorage config = publicService.switchPublic(checkPublic);
//                service.setWxMpConfigStorage(config);
//            }
//        }
//
//    }
}
