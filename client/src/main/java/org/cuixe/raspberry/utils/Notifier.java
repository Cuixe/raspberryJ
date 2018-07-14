package org.cuixe.raspberry.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Notifier {

    private static final Logger logger = LogManager.getLogger("Root");

    public static void notify(Level level, Object object) {
        logger.log(level, object);
    }

    public static void debug(Object object) {
        logger.debug(object);
    }

    public static void info(Object object) {
        logger.info(object);
    }

    public static void warn(Object object) {
        logger.warn(object);
    }

    public static void error(Object object) {
        logger.error(object);
    }
}
