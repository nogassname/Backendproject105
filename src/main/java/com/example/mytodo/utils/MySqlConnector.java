package com.example.mytodo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    public static final String URL = "jdbc:mysql://mysql.cslab.bsthun.com:1205/webtodo";
    public static final String USERNAME = "nn";
    public static final String PASSWORD = "nn00";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
