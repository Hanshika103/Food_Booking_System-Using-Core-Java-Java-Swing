package database; 
// This package contains database-related helper classes

import java.sql.*; 
// Import JDBC classes (Connection, DriverManager, SQLException, etc.)

// This class is responsible for creating database connection
public class DBConnection {

    // Database URL (location of MySQL database)
    private static final String URL = 
        "jdbc:mysql://localhost:3306/food_system_1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    // MySQL username
    private static final String USER = "root";

    // MySQL password
    private static final String PASS = "1111@Han1111"; 
    // ⚠️ In real projects, never hardcode password (use config file or env variables)

    // ==============================
    // METHOD: Get Database Connection
    // ==============================
    public static Connection getConnection() {

        // Connection object (initially null)
        Connection con = null;

        try {

            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection using DriverManager
            con = DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {

            // Print error if connection fails
            e.printStackTrace();
        }

        // Return connection object (may be null if failed)
        return con;
    }
}
