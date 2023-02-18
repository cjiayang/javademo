/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.logbackdemo;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joey Chen
 * @created 2023/2/18 18:17
 */
public class LoggerSelectionRole {
    public static void main(String[] args) {
        // get a logger instance named "com.foo". Let us further assume that the
        // logger is of type  ch.qos.logback.classic.Logger so that we can
        // set its level
        ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        //set its Level to INFO. The setLevel() method requires a logback logger
        logger.setLevel(Level.INFO);

        Logger barlogger = LoggerFactory.getLogger("com.foo.Bar");

        // This request is enabled, because WARN >= INFO
        logger.warn("Low fuel level.");

        // This request is disabled, because DEBUG < INFO.
        logger.debug("Starting search for nearest gas station.");

        // The logger instance barlogger, named "com.foo.Bar",
        // will inherit its level from the logger named
        // "com.foo" Thus, the following request is enabled
        // because INFO >= INFO.
        barlogger.info("Located nearest gas station.");

        // This request is disabled, because DEBUG < INFO.
        barlogger.debug("Exiting gas station search");
    }
}
