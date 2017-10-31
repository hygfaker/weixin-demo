package com.minstone.mobile.mp.common.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangyg
 * @description
 * @since 2017/10/29
 */
public class PraseParamValidate {
    private static final Logger logger = LoggerFactory.getLogger(PraseParamValidate.class);
    public static void check(Object u){
        Class c = u.getClass();
        if (!c.isAnnotationPresent(ParamValidate.class)){
            logger.info("该类不包含【ParamValidate】类型的注解");
        }
        ParamValidate paramValidate = (ParamValidate)c.getAnnotation(ParamValidate.class);
        String param = paramValidate.desc();
    }
}
