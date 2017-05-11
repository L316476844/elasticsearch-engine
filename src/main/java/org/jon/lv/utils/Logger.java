package org.jon.lv.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logger {
    public static void warn(Object obj, String message, Exception e) {
        Log log = LogFactory.getLog(obj.getClass().getName());
        if (log.isWarnEnabled()) {
            if (e == null) {
                log.warn(message);
            } else {
                log.warn(message, e);
            }
        }
    }

    public static void warn(Object obj, String message) {
        warn(obj, message, null);
    }

    public static void error(Object obj, String message) {
        error(obj, message, null);
    }

    public static void error(Class clz, String message) {
        error(clz, message, null);
    }

    public static void error(Object obj, String message, Throwable e) {
        Log log = LogFactory.getLog(obj.getClass().getName());
        if (log.isErrorEnabled()) {
            if (e == null) {
                log.error(message);
            } else {
                log.error(message, e);
            }
        }
    }

    public static void info(Object obj, String message) {
        info(obj.getClass(), message);
    }

    public static void info(Class clz, String message) {
        Log log = LogFactory.getLog(clz.getName());
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public static void debug(Object obj, String message) {
        debug(obj.getClass(), message);
    }

    public static void debug(Class clz, String message) {
        Log log = LogFactory.getLog(clz.getName());
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }
}
