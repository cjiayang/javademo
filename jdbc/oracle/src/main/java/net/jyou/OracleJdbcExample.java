package net.jyou;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleJdbcExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@//ip:1521/ORCL";
        String username = "username";
        String password = "password";

        try {
            // 显式加载驱动（JDBC 4.0+ 后理论上不需要，但某些情况下仍需要）
            Class.forName("oracle.jdbc.OracleDriver");

            // 设置兼容性系统属性
//            System.setProperty("oracle.jdbc.J2EE13Compliant", "true");

            // 获取连接
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("连接成功！");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
