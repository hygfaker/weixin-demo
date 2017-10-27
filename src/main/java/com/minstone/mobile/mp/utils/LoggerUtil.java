package com.minstone.mobile.mp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huangyg on 2017/8/27.
 */
public class LoggerUtil {
    private static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void info(String info){
        logger.info(info);
    }

    public static void debug(String debug){
        logger.info(debug);
    }

    public static void error(String error){
        logger.info(error);
    }
}
