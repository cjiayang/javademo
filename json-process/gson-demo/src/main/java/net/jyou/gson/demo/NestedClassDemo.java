package net.jyou.gson.demo;

import net.jyou.gson.demo.objects.Outer;

/**
 *
 * Gson默认只能序列化静态内部类，非静态内部类需要做处理
 *
 * @author Joey Chen
 * @created 2023/7/30 9:59
 */
public class NestedClassDemo {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.a = "a";

    }

}
