package net.jyou.javase.lang;

import java.util.Properties;

/**
 * @author Joey Chen
 * @created 2023/3/19 17:38
 */
public class SystemDemo {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);
        
    }
}
