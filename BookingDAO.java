


package dao;

import model.Order;
import database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // ==============================
    // 1. Place Order
    // ==============================
    public boolean placeOrder(Order order) {

        String orderSql = "INSERT INTO orders (student_id, order_date, order_time, total_amount, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {

            // Insert into Orders table
            ps.setInt(1, order.getStudentId());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setTime(3, new java.sql.Time(order.getOrderDate().getTime()));
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());

            ps.executeUpdate();

            // Get generated order_id
            ResultSet rs = ps.getGeneratedKeys();
            int orderId = 0;

            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert into Order_Details table
            String detailSql = "INSERT INTO order_details (order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement ps2 = con.prepareStatement(detailSql);

            for (Order.Item item : order.getItemsList()) {
                ps2.setInt(1, orderId);
                ps2.setInt(2, item.getItemId());
                ps2.setInt(3, item.getQuantity());
                ps2.setDouble(4, item.getSubtotal());
                ps2.addBatch();
            }

            ps2.executeBatch();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ==============================
    // 2. Get Orders for a Student
    // ==============================
    public List<Order> getOrdersForUser(int studentId) {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE student_id=? ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

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
    // 3. Get All Orders (Admin)
    // ==============================
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY order_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
    // 4. Update Order Status
    // ==============================
    public boolean updateOrderStatus(int orderId, String status) {

        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

   public boolean markOrderPaid(int orderId, String method) {

    String sql = "INSERT INTO payment (order_id, payment_method, payment_status) VALUES (?, ?, ?)";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, orderId);
        ps.setString(2, method);
        ps.setString(3, "Paid");

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
}