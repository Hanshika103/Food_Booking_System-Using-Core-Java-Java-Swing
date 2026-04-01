package dao;

import database.DBConnection;
import java.sql.*;

public class AdminDAO {

    public boolean validateAdmin(String name, String password) {

        String sql = "SELECT * FROM admin WHERE name=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}