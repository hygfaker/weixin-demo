package com.minstone.wechat.utils.code;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by huangyg on 2017/9/18.
 */

public class IdGen {
    private static SecureRandom random = new SecureRandom();
    public static final String ALL_CHAR_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+";
    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER_CHAR = "0123456789";
    public static final String SPECIAL_CHAR = "!@#$%^&*()_+";

    public IdGen() {
    }

    public static String generateInteger(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
        }

        return sb.toString();
    }

    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static String generateAllString(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+".charAt(random.nextInt("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+".length())));
        }

        return sb.toString();
    }

    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }

        return sb.toString();
    }

    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append('0');
        }

        return sb.toString();
    }

    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if(fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
    }

    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if(fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
            sb.append(strNum);
            return sb.toString();
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
    }

    public static String generatePwdStr() {
        StringBuffer sb = new StringBuffer();

        int i;
        for(i = 0; i < 3; ++i) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
            sb.append("0123456789".charAt(random.nextInt("0123456789".length())));
        }

        for(i = 0; i < 2; ++i) {
            sb.append("!@#$%^&*()_+".charAt(random.nextInt("!@#$%^&*()_+".length())));
        }

        return sb.toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }


    public String getNextId() {
        return uuid();
    }
}
