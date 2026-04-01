package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import dao.BookingDAO;
import model.Order;

public class PaymentFrame extends JFrame {

    private JComboBox<String> orderCombo;
    private JComboBox<String> paymentMethodCombo;
    private JButton payButton, backButton;

    private int studentId; // ✅ using studentId
    private List<Order> userOrders;

    public PaymentFrame(int studentId) {
        this.studentId = studentId;
        initUI();
    }

    private void initUI() {

        setTitle("Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Select Order to Pay", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(title, gbc);

        // ✅ Load orders using studentId
        BookingDAO dao = new BookingDAO();
        userOrders = dao.getOrdersForUser(studentId);

        // ✅ FIXED HERE (totalAmount)
        String[] orderList = userOrders.stream()
                .map(o -> "Order #" + o.getOrderId() + " - ₹" + o.getTotalAmount())
                .toArray(String[]::new);

        orderCombo = new JComboBox<>(orderList);

        gbc.gridy = 1;
        panel.add(orderCombo, gbc);

        // Payment Method
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        panel.add(new JLabel("Payment Method:"), gbc);

        paymentMethodCombo = new JComboBox<>(new String[]{"UPI", "Cash", "Card"});
        gbc.gridx = 1;
        panel.add(paymentMethodCombo, gbc);

        // Buttons
        payButton = new JButton("Pay Now");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(payButton, gbc);

        backButton = new JButton("Back");
        gbc.gridx = 1;
        panel.add(backButton, gbc);

        add(panel);

        payButton.addActionListener(e -> makePayment());
        backButton.addActionListener(e -> goBack());

        setVisible(true);
    }

    private void makePayment() {

    int selectedIndex = orderCombo.getSelectedIndex();

    if (selectedIndex == -1) {
        JOptionPane.showMessageDialog(this, "No order selected.");
        return;
    }

    // ✅ GET PAYMENT METHOD (IMPORTANT)
    String paymentMethod = (String) paymentMethodCombo.getSelectedItem();

    Order order = userOrders.get(selectedIndex);

    BookingDAO dao = new BookingDAO();

    // ✅ PASS METHOD ALSO
    if (dao.markOrderPaid(order.getOrderId(), paymentMethod)) {

        JOptionPane.showMessageDialog(this,
                "Payment successful for Order #" + order.getOrderId()
                        + " via " + paymentMethod);

    } else {

        JOptionPane.showMessageDialog(this, "Payment failed.");
    }
}

    private void goBack() {
        dispose();
        new MenuFrame(studentId, "User"); // ✅ fixed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentFrame(1));
    }
}