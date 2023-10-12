package com.amdocs.doctor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "admin");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC driver not found", e);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
