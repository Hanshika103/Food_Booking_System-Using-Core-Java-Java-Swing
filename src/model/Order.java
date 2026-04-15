package model; // Model package (stores data classes)

import java.util.Date; // For order date/time
import java.util.List; // For storing multiple items in order

// This class represents an Order in the system
public class Order {

    // ==============================
    // FIELDS (ORDER DATA)
    // ==============================

    private int orderId; // unique order ID (from database)
    private int studentId; // which student placed order
    private Date orderDate; // date of order
    private double totalAmount; // total bill amount
    private String status; // order status (Preparing, Ready, Delivered)

    private List<Item> itemsList; // list of items in this order (VERY IMPORTANT)

    // ==============================
    // CONSTRUCTOR 1 (FOR PLACING ORDER)
    // ==============================
    public Order(int studentId, Date orderDate, double totalAmount, String status, List<Item> itemsList) {

        this.studentId = studentId; // assign student ID
        this.orderDate = orderDate; // assign order date
        this.totalAmount = totalAmount; // assign total bill
        this.status = status; // assign status
        this.itemsList = itemsList; // assign list of items
    }

    // ==============================
    // CONSTRUCTOR 2 (FOR FETCHING FROM DATABASE)
    // ==============================
    public Order(int orderId, int studentId, Date orderDate, Date orderTime, double totalAmount, String status) {

        this.orderId = orderId; // assign order ID
        this.studentId = studentId; // assign student ID
        this.orderDate = orderDate; // assign order date
        this.totalAmount = totalAmount; // assign total amount
        this.status = status; // assign status

        // NOTE: orderTime parameter is NOT used (can be removed)
    }

    // ==============================
    // GETTERS (READ DATA)
    // ==============================

    public int getOrderId() {
        return orderId; // return order ID
    }

    public int getStudentId() {
        return studentId; // return student ID
    }

    public Date getOrderDate() {
        return orderDate; // return order date
    }

    public double getTotalAmount() {
        return totalAmount; // return total bill
    }

    public String getStatus() {
        return status; // return order status
    }

    public List<Item> getItemsList() {
        return itemsList; // return list of items in order
    }

    // ==============================
    // SETTERS (UPDATE DATA)
    // ==============================

    public void setStatus(String status) {
        this.status = status; // update status
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList; // update items list
    }

    // ==============================
    // INNER CLASS (ORDER ITEMS)
    // ==============================
    public static class Item {

        private int itemId; // food item ID
        private int quantity; // quantity ordered
        private double subtotal; // price × quantity

        // Constructor for item
        public Item(int itemId, int quantity, double subtotal) {

            this.itemId = itemId; // assign item ID
            this.quantity = quantity; // assign quantity
            this.subtotal = subtotal; // assign subtotal
        }

        // Getter for item ID
        public int getItemId() {
            return itemId; // return item ID
        }

        // Getter for quantity
        public int getQuantity() {
            return quantity; // return quantity
        }

        // Getter for subtotal
        public double getSubtotal() {
            return subtotal; // return subtotal
        }
    }
}
