

package dao;

import model.Registration;
import java.sql.*;
import database.DBConnection;

public class RegistrationDAO {

    // Add new student
    public boolean addRegistration(Registration reg) {
        String sql = "INSERT INTO student (name, email, password) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reg.getName());
            ps.setString(2, reg.getEmail());
            ps.setString(3, reg.getPassword());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get student by email
    public Registration getRegistrationByEmail(String email) {
        String sql = "SELECT * FROM student WHERE email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Registration(
                        rs.getInt("student_id"),   // ✅ changed
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}