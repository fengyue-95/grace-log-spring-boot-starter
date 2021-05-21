package com.fengyue95.autolog.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主要存放类的log实体
 *
 * @author fengyue
 * @date 2021/5/21
 */
public class LoggerCache {

    /**
     * 以全限定类名为key，对应logger为value
     */
    public static Map<String, Logger> loggerCache = new HashMap<String, Logger>();

    public static Logger getLogger(String className) {
        // 从静态map中获取日志实例
        Logger logger = loggerCache.get(className);
        if (logger == null) {
            // LoggerFactory创建一个日志实例
            logger = LoggerFactory.getLogger(className);
            // 加入到静态map中
            loggerCache.put(className, logger);
        }
        return logger;
    }
}
