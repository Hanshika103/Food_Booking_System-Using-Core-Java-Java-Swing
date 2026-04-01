

/*package ui;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    private JButton bookFoodButton, viewOrdersButton, paymentButton, adminLoginButton, logoutButton;
    private String userEmail;

    public MenuFrame(String email) {

        // Store logged-in user email
        this.userEmail = email;

        if(this.userEmail == null || this.userEmail.isEmpty()){
            this.userEmail = "Unknown User";
        }

        initUI();
    }

    private void initUI() {

        setTitle("FoodBooking - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,450);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245,245,245));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome, " + userEmail, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI",Font.BOLD,18));
        welcomeLabel.setForeground(new Color(33,150,243));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        // Book Food
        bookFoodButton = createStyledButton("Book Food", new Color(33,150,243));
        gbc.gridy = 1;
        panel.add(bookFoodButton, gbc);

        bookFoodButton.addActionListener(e -> openBooking());

        // View Orders
        viewOrdersButton = createStyledButton("View My Orders", new Color(76,175,80));
        gbc.gridy = 2;
        panel.add(viewOrdersButton, gbc);

        viewOrdersButton.addActionListener(e -> openOrders());

        // Payment
        paymentButton = createStyledButton("Make Payment", new Color(255,152,0));
        gbc.gridy = 3;
        panel.add(paymentButton, gbc);

        paymentButton.addActionListener(e -> openPayment());

        // Admin Login
        adminLoginButton = createStyledButton("Admin Login", new Color(156,39,176));
        gbc.gridy = 4;
        panel.add(adminLoginButton, gbc);

        adminLoginButton.addActionListener(e -> openAdminLogin());

        // Logout
        logoutButton = createStyledButton("Logout", new Color(244,67,54));
        gbc.gridy = 5;
        panel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> logout());

        add(panel);

        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {

        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI",Font.BOLD,16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        return button;
    }

    private void openBooking() {

        dispose();
        new BookingFrame(userEmail);
    }

    private void openOrders() {

        dispose();
        new OrdersFrame(userEmail);
    }

    private void openPayment() {

        dispose();
        new PaymentFrame(userEmail);
    }

    private void openAdminLogin() {

        dispose();
        new AdminLoginFrame();
    }

    private void logout() {

        dispose();
        new LoginFrame();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new MenuFrame("test@example.com"));
    }
}*/

package ui;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    private JButton bookFoodButton, viewOrdersButton, paymentButton, adminLoginButton, logoutButton;

    private int studentId;
    private String userName;

    public MenuFrame(int studentId, String userName) {

        this.studentId = studentId;
        this.userName = userName;

        if (this.userName == null || this.userName.isEmpty()) {
            this.userName = "User";
        }

        initUI();
    }

    private void initUI() {

        setTitle("FoodBooking - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245,245,245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Welcome, " + userName, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI",Font.BOLD,18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(welcomeLabel, gbc);

        bookFoodButton = createStyledButton("Book Food", new Color(33,150,243));
        gbc.gridy = 1;
        panel.add(bookFoodButton, gbc);
        bookFoodButton.addActionListener(e -> openBooking());

        viewOrdersButton = createStyledButton("View My Orders", new Color(76,175,80));
        gbc.gridy = 2;
        panel.add(viewOrdersButton, gbc);
        viewOrdersButton.addActionListener(e -> openOrders());

        paymentButton = createStyledButton("Make Payment", new Color(255,152,0));
        gbc.gridy = 3;
        panel.add(paymentButton, gbc);
        paymentButton.addActionListener(e -> openPayment());

        adminLoginButton = createStyledButton("Admin Login", new Color(156,39,176));
        gbc.gridy = 4;
        panel.add(adminLoginButton, gbc);
        adminLoginButton.addActionListener(e -> openAdminLogin());

        logoutButton = createStyledButton("Logout", new Color(244,67,54));
        gbc.gridy = 5;
        panel.add(logoutButton, gbc);
        logoutButton.addActionListener(e -> logout());

        add(panel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {

        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI",Font.BOLD,16));
        button.setFocusPainted(false);

        return button;
    }

    // ==============================
    // 🔥 FIXED METHODS
    // ==============================
    private void openBooking() {
        dispose();
        new BookingFrame(studentId, userName); // ✅ FIXED
    }

    private void openOrders() {
        dispose();
        new OrdersFrame(studentId); // ✅ OK
    }

    private void openPayment() {
        dispose();
        new PaymentFrame(studentId); // (make sure constructor exists)
    }

    private void openAdminLogin() {
        dispose();
        new AdminLoginFrame();
    }

    private void logout() {
        dispose();
        new LoginFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame(1, "Test User"));
    }
}