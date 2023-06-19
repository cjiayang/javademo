package net.jyou.javase.jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Joey Chen
 * @created 2023/6/1 8:42
 */
@Slf4j
public class RawJdbc {
    @SneakyThrows
    public static void main(String[] args) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.181.11:3306/xxl_job", "xxl", "xxl_123");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from xxl_job_user");
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            log.info("id: {}, username: {}, password: {}", id, username, password);
        }
    }

}
