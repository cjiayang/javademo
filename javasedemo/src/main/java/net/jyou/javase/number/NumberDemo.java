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

        println("-----------特殊数字的二进制值---------");
        printf("0's binary string is %s", Integer.toBinaryString(0));
        printf("1's binary string is %s", Integer.toBinaryString(1));
        // 11111111111111111111111111111111
        printf("-1's binary string is %s",Integer.toBinaryString(-1));
        // 11111111111111111111111111111111 这里是为啥
        printf("maximun integer 2147483647's binary string is %s",Integer.toBinaryString(Integer.MAX_VALUE));
        // 10000000000000000000000000000000 这里是为啥
        printf("minimun integer -2147483648's binary string is %s", Integer.toBinaryString(Integer.MIN_VALUE));

        println("-----------Byte相关方法---------");
        println(Byte.valueOf("100", 2));
        println(Byte.decode("100"));

        println("-----------Double相关方法---------");
        println(Double.isFinite(1.0));
        println(Double.isNaN(2));
        println(Double.doubleToLongBits(256.0));


        println("-----------(-127~128)缓存测试---------");
        Integer v1 = Integer.valueOf(100);
        Integer v2 = Integer.valueOf(100);
        Integer v3 = Integer.valueOf(200);
        Integer v4 = Integer.valueOf(200);
        printf("v1's memory address is %d", System.identityHashCode(v1));
        printf("v2's memory address is %d", System.identityHashCode(v2));
        printf("v3's memory address is %d", System.identityHashCode(v3));
        printf("v4's memory address is %d", System.identityHashCode(v4));

    }
}
