package dao; 
// DAO package → contains database-related classes (Data Access Layer)

import model.Order; 
// Import Order model (used to transfer order data between UI and DB)

import database.DBConnection; 
// Import your custom DB connection class (returns Connection object)

import java.sql.*; 
// Import JDBC classes (Connection, PreparedStatement, ResultSet, SQLException)

import java.util.ArrayList; 
// Used to store multiple Order objects

import java.util.List; 
// Interface for list collection

// This class handles all database operations related to Booking/Orders
public class BookingDAO {

    // ==============================
    // 1. PLACE ORDER METHOD
    // ==============================
    public boolean placeOrder(Order order) {

        // SQL query to insert main order into orders table
        String orderSql = "INSERT INTO orders (student_id, order_date, order_time, total_amount, status) VALUES (?, ?, ?, ?, ?)";

        // try-with-resources → automatically closes Connection and PreparedStatement
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

            // Set student ID in first ?
            ps.setInt(1, order.getStudentId());

            // Convert java.util.Date → java.sql.Date for DB
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));

            // Convert date to SQL time
            ps.setTime(3, new java.sql.Time(order.getOrderDate().getTime()));

            // Set total amount
            ps.setDouble(4, order.getTotalAmount());

            // Set order status (e.g., Preparing)
            ps.setString(5, order.getStatus());

            // Execute insert query
            ps.executeUpdate();

            // Get auto-generated order ID from DB
            ResultSet rs = ps.getGeneratedKeys();
            int orderId = 0;

            // If ID exists, store it
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // SQL query for inserting order items into order_details table
            String detailSql = "INSERT INTO order_details (order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";

            // Create PreparedStatement for order items (NOT inside try-with-resources ⚠)
            PreparedStatement ps2 = con.prepareStatement(detailSql);

            // Loop through all items in order
            for (Order.Item item : order.getItemsList()) {

                // Set order ID (foreign key)
                ps2.setInt(1, orderId);

                // Set item ID
                ps2.setInt(2, item.getItemId());

                // Set quantity
                ps2.setInt(3, item.getQuantity());

                // Set subtotal (price × quantity)
                ps2.setDouble(4, item.getSubtotal());

                // Add to batch (for bulk insert)
                ps2.addBatch();
            }

            // Execute all batch inserts at once
            ps2.executeBatch();

            // If everything successful
            return true;

        } catch (SQLException e) {
            // Print error if any SQL exception occurs
            e.printStackTrace();
        }

        // If failure
        return false;
    }

    // ==============================
    // 2. GET ORDERS FOR A USER
    // ==============================
    public List<Order> getOrdersForUser(int studentId) {

        // Create list to store orders
        List<Order> orders = new ArrayList<>();

        // SQL query to fetch orders of specific student
        String sql = "SELECT * FROM orders WHERE student_id=? ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set student ID
            ps.setInt(1, studentId);

            // Execute query
            ResultSet rs = ps.executeQuery();

            // Loop through all results
            while (rs.next()) {

                // Create Order object from database row
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("student_id"),
                        rs.getDate("order_date"),
                        rs.getTime("order_time"),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );

                // Add order to list
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return list of orders
        return orders;
    }

    // ==============================
    // 3. GET ALL ORDERS (ADMIN)
    // ==============================
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        // SQL query to fetch all orders
        String sql = "SELECT * FROM orders ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Loop through result set
            while (rs.next()) {

                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("student_id"),
                        rs.getDate("order_date"),
                        rs.getTime("order_time"),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // ==============================
    // 4. UPDATE ORDER STATUS
    // ==============================
    public boolean updateOrderStatus(int orderId, String status) {

        // SQL query to update status
        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set new status
            ps.setString(1, status);

            // Set order ID
            ps.setInt(2, orderId);

            // Execute update and check if rows affected > 0
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==============================
    // 5. MARK ORDER AS PAID
    // ==============================
    public boolean markOrderPaid(int orderId, String method) {

        // SQL query to insert payment record
        String sql = "INSERT INTO payment (order_id, payment_method, payment_status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set order ID
            ps.setInt(1, orderId);

            // Set payment method (UPI/Cash/Card)
            ps.setString(2, method);

            // Set payment status
            ps.setString(3, "Paid");

            // Execute insert and return success/failure
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
