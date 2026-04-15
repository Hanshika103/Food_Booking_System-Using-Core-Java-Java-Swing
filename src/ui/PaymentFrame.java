package ui; // Package name (UI layer of project)

import javax.swing.*; // Swing components (JFrame, JComboBox, JButton, etc.)
import java.awt.*; // Layout, colors, fonts
import java.util.List; // List collection

import dao.BookingDAO; // DAO class for database operations (orders/payment)
import model.Order; // Order model class

// Payment screen for student orders
public class PaymentFrame extends JFrame {

    private JComboBox<String> orderCombo; // dropdown for selecting order
    private JComboBox<String> paymentMethodCombo; // dropdown for payment method
    private JButton payButton, backButton; // buttons

    private int studentId; // logged-in student ID
    private List<Order> userOrders; // list of user's orders from DB

    // Constructor (receives studentId from MenuFrame)
    public PaymentFrame(int studentId) {
        this.studentId = studentId; // store student ID
        initUI(); // build UI
    }

    // ==============================
    // CREATE UI METHOD
    // ==============================
    private void initUI() {

        setTitle("Payment"); // window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app on exit

        setSize(450, 300); // window size

        setLocationRelativeTo(null); // center window

        // Main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(new Color(245, 245, 245)); // light gray background

        GridBagConstraints gbc = new GridBagConstraints(); // layout manager rules

        gbc.insets = new Insets(12, 12, 12, 12); // spacing

        gbc.fill = GridBagConstraints.HORIZONTAL; // stretch horizontally

        // ==============================
        // TITLE LABEL
        // ==============================
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Select Order to Pay", JLabel.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 20)); // font style

        panel.add(title, gbc); // add title

        // ==============================
        // LOAD ORDERS FROM DATABASE
        // ==============================
        BookingDAO dao = new BookingDAO(); // DAO object

        userOrders = dao.getOrdersForUser(studentId); // fetch user's orders

        // Convert orders into dropdown text
        String[] orderList = userOrders.stream()
                .map(o -> "Order #" + o.getOrderId() + " - ₹" + o.getTotalAmount())
                .toArray(String[]::new);

        // Dropdown for selecting order
        orderCombo = new JComboBox<>(orderList);

        gbc.gridy = 1;

        panel.add(orderCombo, gbc); // add dropdown

        // ==============================
        // PAYMENT METHOD LABEL
        // ==============================
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        panel.add(new JLabel("Payment Method:"), gbc);

        // Payment method dropdown
        paymentMethodCombo = new JComboBox<>(new String[]{"UPI", "Cash", "Card"});

        gbc.gridx = 1;

        panel.add(paymentMethodCombo, gbc);

        // ==============================
        // PAY BUTTON
        // ==============================
        payButton = new JButton("Pay Now");

        gbc.gridx = 0;
        gbc.gridy = 3;

        panel.add(payButton, gbc);

        // ==============================
        // BACK BUTTON
        // ==============================
        backButton = new JButton("Back");

        gbc.gridx = 1;

        panel.add(backButton, gbc);

        add(panel); // add panel to frame

        // Button actions
        payButton.addActionListener(e -> makePayment()); // payment logic
        backButton.addActionListener(e -> goBack()); // back navigation

        setVisible(true); // show window
    }

    // ==============================
    // PAYMENT LOGIC
    // ==============================
    private void makePayment() {

        int selectedIndex = orderCombo.getSelectedIndex(); // selected order index

        // If nothing selected
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "No order selected.");
            return;
        }

        // Get selected payment method
        String paymentMethod = (String) paymentMethodCombo.getSelectedItem();

        // Get selected order object
        Order order = userOrders.get(selectedIndex);

        BookingDAO dao = new BookingDAO(); // DAO object

        // Update payment status in database
        if (dao.markOrderPaid(order.getOrderId(), paymentMethod)) {

            JOptionPane.showMessageDialog(this,
                    "Payment successful for Order #" + order.getOrderId()
                            + " via " + paymentMethod);

        } else {

            JOptionPane.showMessageDialog(this, "Payment failed.");
        }
    }

    // ==============================
    // BACK BUTTON ACTION
    // ==============================
    private void goBack() {

        dispose(); // close current window

        new MenuFrame(studentId, "User"); // go back to menu
    }

    // Main method (for testing)
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new PaymentFrame(1));
        // safe UI thread execution
    }
}
