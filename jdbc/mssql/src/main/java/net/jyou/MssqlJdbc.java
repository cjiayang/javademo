package net.jyou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class MssqlJdbc {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        String connectionUrl =
                "jdbc:sqlserver://ip:1433;"
                        + "database=master;"
                        + "user=sa;"
                        + "password=password;"
                        + "trustServerCertificate=true;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            System.out.println("Connected to database.");
            connection.close();
            // Code here.
        } catch (SQLException e) {

            e.printStackTrace();
        }


    }
}
