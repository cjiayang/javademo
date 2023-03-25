package net.jyou.javase.number;

import net.jyou.util.PrintUtil;

/**
 * 复习移位运算相关知识
 * @author Joey Chen
 * @created 2023/3/19 21:23
 */
public class ByteOperation {
    public static void main(String[] args) {
        int i = -1;
        System.out.println("Before << , i's value is " + i);
        System.out.println("i's binary string is " + Integer.toBinaryString(i));
        i <<= 10;
        System.out.println("After << , i's value is " + i);
        System.out.println("i's binary string is " + Integer.toBinaryString(i));
        // 1的补码二进制表示：00000000 00000000 00000000 00000001
        //-1的补码二进制表示：11111111 11111111 11111111 11111111
        //-1的原码二进制表示：10000000 00000000 00000000 00000001


        PrintUtil.println("-----对正数进行右移操作-----");
        // 有符号右移，高位补符号位，低位丢弃
        // 正数右移
        //            00000000 00000000 00010011 10000000
        // 00000000 00000000 00000000 00000100
        int i1 = 4992;
        System.out.println("Before >> , i1's value is " + i1);
        System.out.println("i1's binary string is " + Integer.toBinaryString(i1));
        i1 >>= 10;
        System.out.println("After >> , i1's value is " + i1);
        System.out.println("i1's binary string is " + Integer.toBinaryString(i1));
        PrintUtil.println("-----对负数进行右移操作-----");
        // 负数右移
        //            11111111 11111111 11101100 10000000
        // 11111111 11111111 11111111 11111011
        int i2 = -4992;
        System.out.println("Before >> , i2's value is " + i2);
        System.out.println("i2's binary string is " + Integer.toBinaryString(i2));
        i2 >>= 10;
        System.out.println("After >> , i2's value is " + i2);
        System.out.println("i2's binary string is " + Integer.toBinaryString(i2));

        PrintUtil.println("-----对负数进行无符号右移操作-----");
        // 无符号右移，高位补零，低位丢弃
        //            11111111 11111111 11101100 10000000
        // 00000000 0011111111 11111111 111011
        int i3 = -4992;
        System.out.println("Before >>> , i3's value is " + i3);
        System.out.println("i3's binary string is " + Integer.toBinaryString(i3));
        i3 >>>= 10;
        System.out.println("After >>> , i3's value is " + i3);
        System.out.println("i3's binary string is " + Integer.toBinaryString(i3));

    }
}
