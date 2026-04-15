package ui; // Package name (folder where this class belongs)

import javax.swing.*; // Swing components (JFrame, JButton, JLabel, etc.)
import java.awt.*; // AWT classes (layout, colors, fonts)

// Main menu screen shown after login
public class MenuFrame extends JFrame {

    // Buttons for different actions
    private JButton bookFoodButton, viewOrdersButton, paymentButton, adminLoginButton, logoutButton;

    private int studentId; // Logged-in user ID
    private String userName; // Logged-in user name

    // Constructor (receives user data from LoginFrame)
    public MenuFrame(int studentId, String userName) {

        this.studentId = studentId; // store student ID
        this.userName = userName;   // store username

        // If username is null or empty, set default value
        if (this.userName == null || this.userName.isEmpty()) {
            this.userName = "User";
        }

        initUI(); // build UI
    }

    // ==============================
    // CREATE UI METHOD
    // ==============================
    private void initUI() {

        setTitle("FoodBooking - Menu"); // Window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit app on close

        setSize(450,450); // Window size

        setLocationRelativeTo(null); // Center window on screen

        // Main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(new Color(245,245,245)); // Light background

        GridBagConstraints gbc = new GridBagConstraints(); // Layout rules

        gbc.insets = new Insets(15,15,15,15); // spacing between components

        gbc.fill = GridBagConstraints.HORIZONTAL; // stretch horizontally

        // ==============================
        // WELCOME LABEL
        // ==============================
        JLabel welcomeLabel = new JLabel("Welcome, " + userName, JLabel.CENTER);

        welcomeLabel.setFont(new Font("Segoe UI",Font.BOLD,18)); // font style

        gbc.gridx = 0; // column 0
        gbc.gridy = 0; // row 0

        panel.add(welcomeLabel, gbc); // add label

        // ==============================
        // BOOK FOOD BUTTON
        // ==============================
        bookFoodButton = createStyledButton("Book Food", new Color(33,150,243));

        gbc.gridy = 1;

        panel.add(bookFoodButton, gbc);

        bookFoodButton.addActionListener(e -> openBooking()); // click action

        // ==============================
        // VIEW ORDERS BUTTON
        // ==============================
        viewOrdersButton = createStyledButton("View My Orders", new Color(76,175,80));

        gbc.gridy = 2;

        panel.add(viewOrdersButton, gbc);

        viewOrdersButton.addActionListener(e -> openOrders());

        // ==============================
        // PAYMENT BUTTON
        // ==============================
        paymentButton = createStyledButton("Make Payment", new Color(255,152,0));

        gbc.gridy = 3;

        panel.add(paymentButton, gbc);

        paymentButton.addActionListener(e -> openPayment());

        // ==============================
        // ADMIN LOGIN BUTTON
        // ==============================
        adminLoginButton = createStyledButton("Admin Login", new Color(156,39,176));

        gbc.gridy = 4;

        panel.add(adminLoginButton, gbc);

        adminLoginButton.addActionListener(e -> openAdminLogin());

        // ==============================
        // LOGOUT BUTTON
        // ==============================
        logoutButton = createStyledButton("Logout", new Color(244,67,54));

        gbc.gridy = 5;

        panel.add(logoutButton, gbc);

        logoutButton.addActionListener(e -> logout());

        add(panel); // add panel to frame

        setVisible(true); // show window
    }

    // ==============================
    // REUSABLE BUTTON DESIGN METHOD
    // ==============================
    private JButton createStyledButton(String text, Color bgColor) {

        JButton button = new JButton(text); // create button

        button.setBackground(bgColor); // background color

        button.setForeground(Color.WHITE); // text color

        button.setFont(new Font("Segoe UI",Font.BOLD,16)); // font style

        button.setFocusPainted(false); // remove focus border

        return button; // return styled button
    }

    // ==============================
    // NAVIGATION METHODS
    // ==============================

    // Open booking screen
    private void openBooking() {
        dispose(); // close current window
        new BookingFrame(studentId, userName); // open booking frame
    }

    // Open orders screen
    private void openOrders() {
        dispose();
        new OrdersFrame(studentId); // pass student ID
    }

    // Open payment screen
    private void openPayment() {
        dispose();
        new PaymentFrame(studentId); // payment frame
    }

    // Open admin login
    private void openAdminLogin() {
        dispose();
        new AdminLoginFrame(); // admin login screen
    }

    // Logout and go back to login screen
    private void logout() {
        dispose();
        new LoginFrame(); // login screen
    }

    // Main method (program starts here)
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new MenuFrame(1, "Test User"));
        // Run UI safely on Event Dispatch Thread
    }
}
