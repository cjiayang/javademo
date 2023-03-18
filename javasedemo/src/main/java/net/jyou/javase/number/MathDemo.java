/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.javase.number;

import static net.jyou.util.PrintUtil.println;

/**
 * @author Joey Chen
 * @created 2023/3/18 21:37
 */
public class MathDemo {
    public static void main(String[] args) {
        Math.max(1, 2);
        println(Math.PI);
        println(Math.E);
        println(Math.max(1,2));
        println(Math.min(1,2));

        println("-----------三角函数---------");
        println(Math.sin(45));
        println(Math.cos(45));
        println(Math.tan(45));
        println(Math.atan(45));

        println("-----------估算---------");
        println(Math.ceil(10.2));
        println(Math.floor(10.2));
        println(Math.round(10.2));

        println(Math.pow(4,4));
        println(Math.abs(-2));
    }
}
