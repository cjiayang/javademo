package net.jyou.javase.lang;

import java.net.URL;

/**
 * @author Joey Chen
 * @created 2023/3/3 9:46
 */
public class ClassDemo {
    public static void main(String[] args) {
        Class<ClassDemo> demoClass = ClassDemo.class;
        String className = demoClass.getName();
        URL resource = demoClass.getResource("hello.txt");
    }
}
