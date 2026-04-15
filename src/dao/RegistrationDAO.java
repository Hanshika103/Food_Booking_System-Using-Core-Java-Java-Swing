package dao; 
// DAO layer → contains database logic for Registration (student)

import model.Registration; 
// Model class → represents student data (name, email, password, id)

import java.sql.*; 
// JDBC classes → Connection, PreparedStatement, ResultSet, SQLException

import database.DBConnection; 
// Custom class → gives database Connection

// This class handles database operations for student registration
public class RegistrationDAO {

    // ==============================
    // 1. ADD NEW STUDENT (REGISTER USER)
    // ==============================
    public boolean addRegistration(Registration reg) {

        // SQL query to insert new student into database
        String sql = "INSERT INTO student (name, email, password) VALUES (?, ?, ?)";

        // try-with-resources → auto closes Connection & PreparedStatement
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set name into first ?
            ps.setString(1, reg.getName());

            // Set email into second ?
            ps.setString(2, reg.getEmail());

            // Set password into third ?
            ps.setString(3, reg.getPassword());

            // Execute insert query
            ps.executeUpdate();

            // If successful → return true
            return true;

        } catch (SQLException e) {
            // Print error if something goes wrong in DB
            e.printStackTrace();

            // Return false if insert fails
            return false;
        }
    }

    // ==============================
    // 2. GET STUDENT BY EMAIL
    // ==============================
    public Registration getRegistrationByEmail(String email) {

        // SQL query to fetch student by email
        String sql = "SELECT * FROM student WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set email into query placeholder
            ps.setString(1, email);

            // Execute SELECT query
            ResultSet rs = ps.executeQuery();

            // Check if record exists
            if (rs.next()) {

                // Create and return Registration object from DB data
                return new Registration(

                        rs.getInt("student_id"),   // get ID from DB
                        rs.getString("name"),      // get name
                        rs.getString("email"),     // get email
                        rs.getString("password")   // get password
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return null if no user found
        return null;
    }
}
