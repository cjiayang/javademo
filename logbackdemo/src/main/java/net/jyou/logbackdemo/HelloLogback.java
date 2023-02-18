/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.logbackdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joey Chen
 * @created 2023/2/18 10:30
 */
public class HelloLogback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloLogback.class);
        logger.info("Hello Logback");
    }
}
