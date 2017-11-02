package com.minstone.mobile.mp.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huangyg
 * @description
 * @since 2017/11/1
 */
@Component
public class ValidatorUtil {


    public static <T> void entityValidator(T t,Validator validator, String... validFileds) throws ConstraintViolationException {
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
}
