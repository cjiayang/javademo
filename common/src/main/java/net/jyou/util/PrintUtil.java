package net.jyou.util;

import java.util.Arrays;

/**
 * @author Joey Chen
 * @created 2023/3/13 15:07
 */
public class PrintUtil {
    public static void println(Object msg) {
        System.out.println(msg);
    }

    /**
     *
     * @param msg1 第一个打印参数
     * @param msg2 任意参数
     */
    public static void println(Object msg1, Object... msg2) {
        StringBuilder sb = new StringBuilder(msg1.toString());
        Arrays.stream(msg2).forEach(sb::append);
        System.out.println(sb);
    }

    /**
     * 格式化打印
     * @param format 格式化字符串
     * @param args 参数
     */
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }
}
