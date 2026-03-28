package database;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/food_system_1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    // private String url =
    // "jdbc:mysql://localhost:3306/foodboosystem?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "1111@Han1111"; // replace with your DB password

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}