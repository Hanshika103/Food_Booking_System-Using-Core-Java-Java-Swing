package dao; 
// DAO package (Data Access Object layer - handles database operations)

import database.DBConnection; 
// Import database connection class (your custom class for MySQL connection)

import java.sql.*; 
// Import all SQL classes (Connection, PreparedStatement, ResultSet, SQLException)

// This class handles database operations related to Admin login
public class AdminDAO {

    // ==============================
    // METHOD: Validate Admin Login
    // ==============================
    public boolean validateAdmin(String name, String password) {

        // SQL query to check admin credentials in database
        String sql = "SELECT * FROM admin WHERE name=? AND password=?";

        try (
            Connection con = DBConnection.getConnection(); 
            // Establish connection to database

            PreparedStatement ps = con.prepareStatement(sql)
            // Precompiled SQL query (prevents SQL injection)
        ) {

            // Set first parameter (?) = name entered by user
            ps.setString(1, name);

            // Set second parameter (?) = password entered by user
            ps.setString(2, password);

            // Execute query and get result set
            ResultSet rs = ps.executeQuery();

            // rs.next() means:
            // if any record exists → login is valid
            return rs.next();

        } catch (SQLException e) {
            // If any database error occurs, print error
            e.printStackTrace();
        }

        // If login fails or error occurs, return false
        return false;
    }
}
