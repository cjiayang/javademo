package net.jyou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlJdbc {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(DRIVER);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("连接成功");
            conn.close();
        } catch (SQLException e) {
            System.out.println("连接失败");
            throw new RuntimeException(e);
        }
    }

}
