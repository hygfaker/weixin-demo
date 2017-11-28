package com.minstone.mobile.mp.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huangyg
 * @description
 * @since 2017/11/1
 */
@Component
public class ValidatorUtil {

    /**
     * 方法校验 - 校验参数必须传入
     * @param t 需要验证的实体类型
     * @param validator
     * @param validFileds 需要验证的字段
     * @return void
     * @author huangyg
     */
    public static <T> void mustParam(T t, Validator validator, String... validFileds) throws ConstraintViolationException {
        for (String filed : validFileds) {
            Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(t, filed);
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            if (iterator.hasNext()) {
                // 取出注解上的message
                String message = iterator.next().getMessage();
                throw new ValidationException(message);
            }
        }
    }
//
//    /**
//     * 方法校验 - 校验参数数组中必然有一个能够校验到，不需要全部校验
//     * @param t 需要验证的实体类型
//     * @param validator
//     * @param validFileds 需要验证的字段
//     * @return void
//     * @author huangyg
//     */
    public static <T> void orParam(T t, Validator validator, String... validFileds) throws ConstraintViolationException {
        for (String filed : validFileds) {
            Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(t, filed);
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            if (iterator.hasNext()) {
                continue;
            } else{
                 return;
            }
        }
        throw new ValidationException("参数缺失,请传入" + Arrays.toString(validFileds) + "中的某一个参数");
    }
}
