/*package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import dao.BookingDAO;
import model.Order;

public class AdminOrdersFrame extends JFrame {

    private JTable ordersTable;
    private JButton logoutButton;

    public AdminOrdersFrame() {
        initUI();
    }

    private void initUI() {

        setTitle("Admin - All Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 450);
        setResizable(true);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("All Orders", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(156, 39, 176));
        panel.add(title, BorderLayout.NORTH);

        // Table
        String[] columns = {
                "Order ID",
                "User Email",
                "Items & Quantity",
                "Total Price",
                "Paid",
                "Status",
                "Order Date"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ordersTable = new JTable(model);

        ordersTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ordersTable.setRowHeight(25);

        ordersTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        ordersTable.getTableHeader().setBackground(new Color(33, 150, 243));
        ordersTable.getTableHeader().setForeground(Color.WHITE);

        // Alternate row colors
        ordersTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 230, 250));
                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadOrders(model);

        // Buttons
        JButton menuButton = new JButton("Manage Menu");
        JButton statusButton = new JButton("Update Status");
        logoutButton = new JButton("Logout");

        menuButton.setBackground(new Color(76, 175, 80));
        statusButton.setBackground(new Color(33, 150, 243));
        logoutButton.setBackground(new Color(244, 67, 54));

        menuButton.setForeground(Color.WHITE);
        statusButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);

        menuButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        menuButton.setFocusPainted(false);
        statusButton.setFocusPainted(false);
        logoutButton.setFocusPainted(false);

        // Button Actions
        menuButton.addActionListener(e -> new AdminMenuFrame());

        statusButton.addActionListener(e -> updateStatus());

        logoutButton.addActionListener(e -> {
            dispose();
            new AdminLoginFrame();
        });

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(245, 245, 245));

        southPanel.add(menuButton);
        southPanel.add(statusButton);
        southPanel.add(logoutButton);

        panel.add(southPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // Load Orders
    private void loadOrders(DefaultTableModel model) {

        BookingDAO dao = new BookingDAO();
        List<Order> orders = dao.getAllOrders();

        for (Order order : orders) {

            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getUserEmail(),
                    order.getItems(),
                    order.getTotalPrice(),
                    order.isPaid() ? "Yes" : "No",
                    order.getStatus(),
                    order.getOrderDate()
            });
        }
    }

    // Update Order Status
    private void updateStatus() {

        int row = ordersTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an order first");
            return;
        }

        int orderId = (int) ordersTable.getValueAt(row, 0);

        String[] options = {"Preparing", "Ready", "Delivered"};

        String status = (String) JOptionPane.showInputDialog(
                this,
                "Select Status",
                "Update Order Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (status != null) {

            BookingDAO dao = new BookingDAO();

            if (dao.updateOrderStatus(orderId, status)) {

                JOptionPane.showMessageDialog(this, "Status Updated");

                dispose();
                new AdminOrdersFrame();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminOrdersFrame::new);
    }
}*/


package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import dao.BookingDAO;
import model.Order;

public class AdminOrdersFrame extends JFrame {

    private JTable ordersTable;
    private JButton logoutButton;

    public AdminOrdersFrame() {
        initUI();
    }

    private void initUI() {

        setTitle("Admin - All Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(750, 450);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("All Orders", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panel.add(title, BorderLayout.NORTH);

        // ✅ FIXED COLUMNS
        String[] columns = {
                "Order ID",
                "Student ID",
                "Total Amount",
                "Status",
                "Order Date"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ordersTable = new JTable(model);

        ordersTable.setRowHeight(25);

        // Alternate row colors
        ordersTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 230, 250));
                }

                return c;
            }
        });

        panel.add(new JScrollPane(ordersTable), BorderLayout.CENTER);

        loadOrders(model);

        // Buttons
        JButton menuButton = new JButton("Manage Menu");
        JButton statusButton = new JButton("Update Status");
        logoutButton = new JButton("Logout");

        menuButton.addActionListener(e -> new AdminMenuFrame());

        statusButton.addActionListener(e -> updateStatus());

        logoutButton.addActionListener(e -> {
            dispose();
            new AdminLoginFrame();
        });

        JPanel southPanel = new JPanel();
        southPanel.add(menuButton);
        southPanel.add(statusButton);
        southPanel.add(logoutButton);

        panel.add(southPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // ==============================
    // 🔥 FIXED LOAD METHOD
    // ==============================
    private void loadOrders(DefaultTableModel model) {

        BookingDAO dao = new BookingDAO();
        List<Order> orders = dao.getAllOrders();

        for (Order order : orders) {

            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getStudentId(),      // ✅ FIXED
                    order.getTotalAmount(),    // ✅ FIXED
                    order.getStatus(),
                    order.getOrderDate()
            });
        }
    }

    // ==============================
    // UPDATE STATUS
    // ==============================
    private void updateStatus() {

        int row = ordersTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an order first");
            return;
        }

        int orderId = (int) ordersTable.getValueAt(row, 0);

        String[] options = {"Preparing", "Ready", "Delivered"};

        String status = (String) JOptionPane.showInputDialog(
                this,
                "Select Status",
                "Update Order Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (status != null) {

            BookingDAO dao = new BookingDAO();

            if (dao.updateOrderStatus(orderId, status)) {

                JOptionPane.showMessageDialog(this, "Status Updated");

                dispose();
                new AdminOrdersFrame();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminOrdersFrame::new);
    }
}