package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=multiLanguage;encrypt=false";
    private static final String USER = "sa"; // thay bằng user SQL của m
    private static final String PASS = "123"; // thay bằng mật khẩu

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.err.println("❌ DB Connection failed: " + e.getMessage());
            return null;
        }
    }
}
