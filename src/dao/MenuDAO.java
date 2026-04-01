/*package dao;

import database.DBConnection;
import model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    public List<MenuItem> getAllItems() {

        List<MenuItem> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM menu_item";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                MenuItem item = new MenuItem(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getString("availability_status")
                );

                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addItem(MenuItem item) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO menu_item(item_name,category,price,availability_status) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, item.getItemName());
            ps.setString(2, item.getCategory());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getAvailabilityStatus());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updatePrice(int id, double price){

    try{

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE menu_item SET price=? WHERE item_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDouble(1,price);
        ps.setInt(2,id);

        ps.executeUpdate();

        return true;

    }catch(Exception e){
        e.printStackTrace();
    }

    return false;
}

    public boolean deleteItem(int id) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM menu_item WHERE item_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
*/


package dao;

import database.DBConnection;
import model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    // ==============================
    // GET ALL MENU ITEMS
    // ==============================
    public List<MenuItem> getAllItems() {

        List<MenuItem> list = new ArrayList<>();

        String sql = "SELECT * FROM menu_item"; // ✅ keep lowercase (MySQL safe)

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                MenuItem item = new MenuItem(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getBoolean("availability_status") // ✅ BOOLEAN
                );

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ==============================
    // ADD ITEM
    // ==============================
    public boolean addItem(MenuItem item) {

        String sql = "INSERT INTO menu_item (item_name, category, price, availability_status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, item.getItemName());
            ps.setString(2, item.getCategory());
            ps.setDouble(3, item.getPrice());
            ps.setBoolean(4, item.isAvailable()); // ✅ FIXED

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==============================
    // UPDATE PRICE
    // ==============================
    public boolean updatePrice(int id, double price) {

        String sql = "UPDATE menu_item SET price=? WHERE item_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, price);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==============================
    // DELETE ITEM
    // ==============================
    public boolean deleteItem(int id) {

        String sql = "DELETE FROM menu_item WHERE item_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}