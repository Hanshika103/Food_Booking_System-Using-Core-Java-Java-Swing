package dao; 
// DAO package → contains database logic classes (Data Access Layer)

import java.sql.*; 
// Import JDBC classes (Connection, PreparedStatement, ResultSet, SQLException)

import database.DBConnection; 
// Import custom DB connection class (gives Connection object)

import model.Registration; 
// Import Registration model (used to return user data)

// This class handles login validation from database
public class LoginDAO {

    // ==============================
    // METHOD: Validate User Login
    // ==============================
    public Registration validate(Registration user) {

        // SQL query to check if email & password exist in student table
        String sql = "SELECT * FROM student WHERE email=? AND password=?";

        // try-with-resources → auto closes Connection & PreparedStatement
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set first ? = email from user object
            ps.setString(1, user.getEmail());

            // Set second ? = password from user object
            ps.setString(2, user.getPassword());

            // Execute query and get result
            ResultSet rs = ps.executeQuery();

            // Check if any record exists
            if (rs.next()) {

                // If user found → return full user data from DB
                return new Registration(

                        rs.getInt("student_id"), // get ID from DB
                        rs.getString("name"),    // get name from DB
                        rs.getString("email"),   // get email from DB
                        rs.getString("password") // get password from DB
                );
            }

        } catch (SQLException e) {
            // Print error if SQL exception occurs
            e.printStackTrace();
        }

        // If user not found → return null
        return null;
    }
}
