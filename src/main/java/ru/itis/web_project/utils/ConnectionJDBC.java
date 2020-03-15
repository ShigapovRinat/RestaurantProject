package ru.itis.web_project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionJDBC {
    private static Connection connection;
    private static final String URL_ADDRESS = "jdbc:mysql://localhost:3306/restaurant_db?serverTimezone=Europe/Moscow";
    private static final String USER = "root";
    private static final String PASSWORD = "ifrehjdlbyfh";

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL_ADDRESS, USER, PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
