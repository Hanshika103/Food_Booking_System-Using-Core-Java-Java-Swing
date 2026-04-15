package ui; // Package name (folder where this class is stored)

import javax.swing.*; // Swing components (JFrame, JTable, JButton, etc.)
import javax.swing.table.DefaultTableCellRenderer; // For custom table row styling
import javax.swing.table.DefaultTableModel; // For managing table data
import java.awt.*; // Layouts, colors, fonts
import java.util.List; // List collection

import dao.BookingDAO; // DAO class for database order operations
import model.Order; // Model class representing an order

// Admin dashboard frame to display and manage all orders
public class AdminOrdersFrame extends JFrame {

    private JTable ordersTable; // Table to display orders
    private JButton logoutButton; // Logout button

    // Constructor
    public AdminOrdersFrame() {
        initUI(); // Build UI
    }

    // Method to build UI
    private void initUI() {

        setTitle("Admin - All Orders"); // Window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close app on exit

        setLocationRelativeTo(null); // Center window

        setSize(750, 450); // Window size

        // Main panel using BorderLayout
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setBackground(new Color(245, 245, 245)); // Light background color

        // Title label
        JLabel title = new JLabel("All Orders", JLabel.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Set font style

        panel.add(title, BorderLayout.NORTH); // Add title at top

        // ==========================
        // TABLE COLUMNS
        // ==========================
        String[] columns = {
                "Order ID",
                "Student ID",
                "Total Amount",
                "Status",
                "Order Date"
        };

        // Table model (data container)
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // JTable using model
        ordersTable = new JTable(model);

        ordersTable.setRowHeight(25); // Row height

        // ==========================
        // ROW COLOR STYLING
        // ==========================
        ordersTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // Alternate row colors (zebra effect)
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 230, 250));
                }

                return c;
            }
        });

        // Add table inside scroll pane
        panel.add(new JScrollPane(ordersTable), BorderLayout.CENTER);

        // Load data from database into table
        loadOrders(model);

        // ==========================
        // BUTTONS
        // ==========================
        JButton menuButton = new JButton("Manage Menu"); // Open menu frame
        JButton statusButton = new JButton("Update Status"); // Change order status
        logoutButton = new JButton("Logout"); // Logout button

        // Button actions
        menuButton.addActionListener(e -> new AdminMenuFrame());
        statusButton.addActionListener(e -> updateStatus());

        logoutButton.addActionListener(e -> {
            dispose(); // Close current frame
            new AdminLoginFrame(); // Go back to login screen
        });

        // Bottom panel for buttons
        JPanel southPanel = new JPanel();

        southPanel.add(menuButton);
        southPanel.add(statusButton);
        southPanel.add(logoutButton);

        panel.add(southPanel, BorderLayout.SOUTH); // Add buttons at bottom

        add(panel); // Add main panel to frame

        setVisible(true); // Show window
    }

    // ==============================
    // LOAD ORDERS FROM DATABASE
    // ==============================
    private void loadOrders(DefaultTableModel model) {

        BookingDAO dao = new BookingDAO(); // DAO object

        List<Order> orders = dao.getAllOrders(); // Fetch all orders

        // Loop through each order and add to table
        for (Order order : orders) {

            model.addRow(new Object[]{
                    order.getOrderId(),     // Order ID
                    order.getStudentId(),   // Student ID
                    order.getTotalAmount(), // Total amount
                    order.getStatus(),      // Order status
                    order.getOrderDate()    // Date
            });
        }
    }

    // ==============================
    // UPDATE ORDER STATUS
    // ==============================
    private void updateStatus() {

        int row = ordersTable.getSelectedRow(); // Get selected row

        if (row == -1) { // If no row selected
            JOptionPane.showMessageDialog(this, "Select an order first");
            return;
        }

        // Get order ID from table
        int orderId = (int) ordersTable.getValueAt(row, 0);

        // Status options
        String[] options = {"Preparing", "Ready", "Delivered"};

        // Show dropdown dialog
        String status = (String) JOptionPane.showInputDialog(
                this,
                "Select Status",
                "Update Order Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // If user selected a status
        if (status != null) {

            BookingDAO dao = new BookingDAO(); // DAO object

            // Update status in database
            if (dao.updateOrderStatus(orderId, status)) {

                JOptionPane.showMessageDialog(this, "Status Updated");

                dispose(); // Close current frame
                new AdminOrdersFrame(); // Reload updated data
            }
        }
    }

    // Main method (program starts here)
    public static void main(String[] args) {

        SwingUtilities.invokeLater(AdminOrdersFrame::new);
        // Run UI safely on Event Dispatch Thread
    }
}
