

/*package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import dao.LoginDAO;
import model.Registration;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginFrame() {

        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420,300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245,245,245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Login to Your Account", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(33,150,243));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(emailLabel, gbc);

        // Email Field
        emailField = new JTextField(20);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBorder(new LineBorder(Color.GRAY,1,true));

        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        // Password Field
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(new LineBorder(Color.GRAY,1,true));

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(33,150,243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        // Register Button
        registerButton = new JButton("Go to Register");
        registerButton.setBackground(new Color(76,175,80));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setFocusPainted(false);

        gbc.gridy = 4;
        mainPanel.add(registerButton, gbc);

        add(mainPanel);

        // Actions
        loginButton.addActionListener(e -> loginAction());
        registerButton.addActionListener(e -> openRegistration());

        setVisible(true);
    }

    private void loginAction() {

        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if(email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please enter email and password");
            return;
        }

        Registration user = new Registration("", email, password);

        LoginDAO dao = new LoginDAO();

        boolean validUser = dao.validate(user);

        if(validUser) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");

            dispose();

            // PASS CORRECT EMAIL TO NEXT SCREEN
            new MenuFrame(email);

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Email or Password");
        }
    }

    private void openRegistration() {

        dispose();

        new RegistrationFrame();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}*/

package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import dao.LoginDAO;
import model.Registration;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginFrame() {

        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420,300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245,245,245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,12,12,12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login to Your Account", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(33,150,243));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setBorder(new LineBorder(Color.GRAY,1,true));
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setBorder(new LineBorder(Color.GRAY,1,true));
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(33,150,243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        registerButton = new JButton("Go to Register");
        registerButton.setBackground(new Color(76,175,80));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        gbc.gridy = 4;
        mainPanel.add(registerButton, gbc);

        add(mainPanel);

        loginButton.addActionListener(e -> loginAction());
        registerButton.addActionListener(e -> openRegistration());

        setVisible(true);
    }

    // ==============================
    // 🔥 FINAL LOGIN METHOD
    // ==============================
    private void loginAction() {

    String email = emailField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    if(email.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Please enter email and password");
        return;
    }

    LoginDAO dao = new LoginDAO();

    Registration user = dao.validate(
            new Registration("", email, password)
    );

    if(user != null) {

        JOptionPane.showMessageDialog(this,
                "Login Successful!");

        dispose();

        // ✅ FIXED HERE
        new MenuFrame(user.getRegId(), user.getName());

    } else {
        JOptionPane.showMessageDialog(this,
                "Invalid Email or Password");
    }
}

    private void openRegistration() {
        dispose();
        new RegistrationFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}