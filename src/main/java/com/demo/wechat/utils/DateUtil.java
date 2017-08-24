package com.demo.wechat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangyg on 2017/8/8.
 */
public class DateUtil {

    public static String getStringDate(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date dateToUnixTimestamp(String dateStr, String pattern) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(pattern).parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date(timestamp);
    }

    public static Date dateToUnixTimestamp(String dateStr) {
        String d = dateStr.trim();
        String pattern = DatePattern.getPatternByDateStr(dateStr);
        return dateToUnixTimestamp(d, pattern);
    }


    /** 日期格式枚举类，根据需要添加其他格式 **/
    public enum DatePattern{
        ISO_SECOND("yyyy-MM-dd'T'HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$"),
        ISO_MINUTE("yyyy-MM-dd'T'HH:mm", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$"),
        DATE_TIME("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"),
        DATE_ONLY("yyyy-MM-dd", "^\\d{4}-\\d{2}-\\d{2}$"),
        YEAR_MONTH("yyyy-MM", "^\\d{4}-\\d{2}$");

        DatePattern(String pattern, String regex){
            this.pattern = pattern;
            this.regex = regex;
        }

        public String getPattern() {
            return pattern;
        }
        public String getRegex() {
            return regex;
        }

        private String pattern;
        private String regex;

        /**
         * 根据日期字符串，判断该日期的格式类型。
         *
         * @param dateStr 日期字符串
         * @return 日期的格式类型，比如getPatternByDateStr("2016-04-27 10:15:08")返回："yyyy-MM-dd HH:mm:ss"
         */
        public static String getPatternByDateStr(String dateStr){
            for(DatePattern df : DatePattern.values()){
                if(RegexUtil.matches(dateStr, df.getRegex())){
                    return df.getPattern();
                }
            }
            return null;
        }
    }


}
