


package dao;

import java.sql.*;
import database.DBConnection;
import model.Registration;

public class LoginDAO {

    
    public Registration validate(Registration user) {

    String sql = "SELECT * FROM student WHERE email=? AND password=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Registration(
                    rs.getInt("student_id"),
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