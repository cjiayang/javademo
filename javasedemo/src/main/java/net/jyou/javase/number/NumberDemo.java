/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.javase.number;

import static net.jyou.util.PrintUtil.*;

import java.io.PipedReader;
import java.math.BigInteger;

/**
 * @author Joey Chen
 * @created 2023/3/18 10:43
 */
public class NumberDemo {
    public static void main(String[] args) {
        println("-----------实例方法---------");
        Integer int2 = 256;
        println(int2.byteValue());
        println(int2.shortValue());
        println(int2.longValue());
        println(int2.doubleValue());
        println(int2.floatValue());
        println(int2.toString());
        println(int2.compareTo(2));
        println(int2.compareTo(257));
        println(int2.toString());

        println("-----------常量---------");
        println(Integer.MAX_VALUE);
        println(Integer.MIN_VALUE);
        println(Integer.SIZE);
        println(Integer.TYPE);
        println(Integer.BYTES);

        println("-----------实例方法---------");
        println(Integer.valueOf("256"));
        println(Integer.valueOf("256", 8));
        println(Integer.parseInt("1000", 2));
        println(Integer.parseInt("256"));

        println("-----------比较与计算---------");
        println(Integer.compare(1, 2));
        println(Integer.max(1, 2));
        println(Integer.min(1, 2));
        println(Integer.sum(1, 2));
        println(Integer.signum(256)); //返回数值符号

        println("-----------比特位换算---------");
        println(Integer.bitCount(256));
        println(Integer.highestOneBit(256));
        println(Integer.lowestOneBit(255));
        println(Integer.reverse(256));
        println(Integer.reverseBytes(256));

        println("-----------数制转换---------");
        println(Integer.toBinaryString(256));
        println(Integer.toOctalString(256));
        println(Integer.toHexString(256));

        println("-----------Byte相关方法---------");
        println(Byte.valueOf("100", 2));
        println(Byte.decode("100"));

        println("-----------Double相关方法---------");
        println(Double.isFinite(1.0));
        println(Double.isNaN(2));
        println(Double.doubleToLongBits(256.0));

    }
}
