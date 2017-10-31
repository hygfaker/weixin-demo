package com.minstone.mobile.mp.common.annotation;

import javax.xml.bind.Element;
import java.lang.annotation.*;

/**
 * @author huangyg
 * @description
 * @since 2017/10/29
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ParamValidate {
    String desc(); // 如果注解只有一个成员则必须使用 value()，使用的时候可以忽略成员名和赋值号（）
    String author(); // 成员以无参无异常方式生命
    int age() default 18; // 可以用 default 为成员指定默认值
}
