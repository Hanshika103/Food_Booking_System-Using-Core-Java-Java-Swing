


package ui;

import javax.swing.*;
import java.awt.*;
//import javax.swing.border.LineBorder;

import dao.AdminDAO;

public class AdminLoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public AdminLoginFrame() {
        initUI();
    }

    private void initUI() {

        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Admin Login", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        backButton = new JButton("Back");
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        add(panel);

        loginButton.addActionListener(e -> adminLogin());
        backButton.addActionListener(e -> goBack());

        setVisible(true);
    }

    // ==============================
    // 🔥 FIXED LOGIN
    // ==============================
    private void adminLogin() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        AdminDAO dao = new AdminDAO();

        if (dao.validateAdmin(username, password)) {

            JOptionPane.showMessageDialog(this, "Admin Login Successful");

            dispose();
            new AdminOrdersFrame(); // your admin panel

        } else {

            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }

    private void goBack() {

        dispose();

        // ⚠️ temporary (you can improve later)
        new LoginFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLoginFrame::new);
    }
}