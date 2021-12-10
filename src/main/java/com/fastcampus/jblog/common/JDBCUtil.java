package com.fastcampus.jblog.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            // JDBC 드라이버 로드
            DriverManager.registerDriver(new org.h2.Driver());

            // JDBC 커넥션 정보 가져오기
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/application.properties"));
            URL = properties.getProperty("spring.datasource.hikari.jdbc-url");
            USER = properties.getProperty("spring.datasource.hikari.username");
            PASSWORD = properties.getProperty("spring.datasource.hikari.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Statement stmt, Connection conn) {
        try {
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
