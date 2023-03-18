/*
 * Copyright (c) 2005, 2023, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.jyou.javase.string;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static net.jyou.util.PrintUtil.*;

/**
 * @author Joey Chen
 * @created 2023/3/18 21:33
 */
public class StringDemo {
    public static void main(String[] args) {
        String statement = "When working with numbers, most of the time you use the primitive types in your code.";
        println(statement.length());

        println("-----------索引定位---------");
        println(statement.indexOf('m'));
        println(statement.indexOf('m',20));
        println(statement.indexOf("in"));
        println(statement.indexOf("in", 20));
        println(statement.lastIndexOf("in"));
        println(statement.lastIndexOf("in", 10));
        println(statement.charAt(10));

        println("-----------字符串比较---------");
        println(statement.compareTo("When"));
        println(statement.compareToIgnoreCase("When"));

        println("-----------字符串判断---------");
        println(statement.contains("the"));
        println(statement.endsWith("."));
        println(statement.startsWith("When"));
        println(statement.contentEquals("When"));
        println(statement.isBlank());
        println(statement.isEmpty());
        println(statement.equals("hello"));
        println(statement.equalsIgnoreCase("hello"));

        println("-----------字符串匹配---------");
        println(statement.matches("\\*"));
        println(statement.regionMatches(1,"\\*",1,10));

        println("-----------字符串操作---------");
        println(statement.concat("你好，字符串"));
        println(statement.repeat(2));
        println(statement.replaceAll("of", "hello"));
        println(statement.replaceFirst("of", "hello"));
        println(statement.toLowerCase(Locale.ROOT));
        println(statement.toUpperCase(Locale.ROOT));
        println(statement.toCharArray());
        println(statement.intern());
        println(statement.split(" "));
        println(statement.split(" ", 10));
        println(statement.strip());
        println(statement.stripLeading());
        println(statement.trim());
        println(statement.substring(10));
        println(statement.substring(10,20));
        println(statement.subSequence(1, 20));

        println("-----------遍历---------");
        println(statement.lines());
        println(statement.chars());


        println("-----------转换---------");
        println(statement.getBytes(StandardCharsets.UTF_8));
        println(statement.getBytes());
        println(new String(statement.getBytes(), StandardCharsets.UTF_8));

        println(statement.codePointAt(10));
        println(statement.codePointBefore(10));
        println(statement.codePoints().count());

    }
}
