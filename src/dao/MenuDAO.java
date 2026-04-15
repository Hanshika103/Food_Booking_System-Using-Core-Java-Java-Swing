package dao; 
// DAO layer → contains database operations related to Menu

import database.DBConnection; 
// Custom class → provides database Connection

import model.MenuItem; 
// Model class → represents menu item data

import java.sql.*; 
// JDBC classes → Connection, PreparedStatement, ResultSet, SQLException

import java.util.ArrayList;
import java.util.List;

// This class handles all database operations for menu items
public class MenuDAO {

    // ==============================
    // GET ALL MENU ITEMS FROM DATABASE
    // ==============================
    public List<MenuItem> getAllItems() {

        // Create empty list to store menu items from DB
        List<MenuItem> list = new ArrayList<>();

        // SQL query to fetch all rows from menu_item table
        String sql = "SELECT * FROM menu_item"; 
        // (MySQL safe lowercase table name)

        // try-with-resources → automatically closes connection, statement, resultset
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Loop through each row from result set
            while (rs.next()) {

                // Create MenuItem object from database row
                MenuItem item = new MenuItem(

                        rs.getInt("item_id"),              // get item ID
                        rs.getString("item_name"),         // get item name
                        rs.getString("category"),          // get category
                        rs.getDouble("price"),             // get price
                        rs.getBoolean("availability_status") // get availability (true/false)
                );

                // Add object to list
                list.add(item);
            }

        } catch (SQLException e) {
            // Print error if database operation fails
            e.printStackTrace();
        }

        // Return final list of menu items
        return list;
    }

    // ==============================
    // ADD NEW MENU ITEM
    // ==============================
    public boolean addItem(MenuItem item) {

        // SQL insert query with placeholders (?)
        String sql = "INSERT INTO menu_item (item_name, category, price, availability_status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set values for SQL query
            ps.setString(1, item.getItemName());        // name
            ps.setString(2, item.getCategory());        // category
            ps.setDouble(3, item.getPrice());           // price
            ps.setBoolean(4, item.isAvailable());       // availability

            // Execute insert query
            ps.executeUpdate();

            // Return success
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return false if failed
        return false;
    }

    // ==============================
    // UPDATE PRICE OF ITEM
    // ==============================
    public boolean updatePrice(int id, double price) {

        // SQL update query
        String sql = "UPDATE menu_item SET price=? WHERE item_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set new price
            ps.setDouble(1, price);

            // Set item ID
            ps.setInt(2, id);

            // Execute update query and get number of rows affected
            int rows = ps.executeUpdate();

            // return true if at least 1 row updated
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==============================
    // DELETE MENU ITEM
    // ==============================
    public boolean deleteItem(int id) {

        // SQL delete query
        String sql = "DELETE FROM menu_item WHERE item_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set item ID to delete
            ps.setInt(1, id);

            // Execute delete and get affected rows
            int rows = ps.executeUpdate();

            // return true if deletion successful
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
