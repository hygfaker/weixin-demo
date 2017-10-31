package com.minstone.mobile.mp.common.annotation;

/**
 * @author huangyg
 * @description
 * @since 2017/10/29
 */
public class test {
    public static void main(String[] args) {
        param();
    }

    @ParamValidate(desc = "描述",author = "yans67",age = 18)
    public static void param(){
        System.out.println("-----");
        PraseParamValidate.check("测试");
        System.out.println("=====");
    }
}
