/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.logbackdemo;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joey Chen
 * @created 2023/2/18 11:49
 */
public class PrintLoggerStatus {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(PrintLoggerStatus.class);
        logger.debug("Hello logback.");
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
