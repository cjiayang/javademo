package net.jyou;

import java.sql.Connection;
import java.sql.DriverManager;

public class DmJdbcExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:dm://ip:5236";
        String username = "username";
        String password = "password";

        try {
            // 显式加载驱动（JDBC 4.0+ 后理论上不需要，但某些情况下仍需要）
            Class.forName("dm.jdbc.driver.DmDriver");

            // 获取连接
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("dameng连接成功！");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
