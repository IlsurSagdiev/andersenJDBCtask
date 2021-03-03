package com.andersen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/andersen";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "spring_course";

    private Connection connection;

    public DBConnect() {
        try {
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Connection succeful!");
    }

    public Connection getConnection() {
        return connection;
    }
}
