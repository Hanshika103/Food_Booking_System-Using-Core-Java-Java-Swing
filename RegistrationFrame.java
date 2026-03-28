/*package ui;

import javax.swing.*;
import java.awt.*;
import dao.RegistrationDAO;
import model.Registration;

public class RegistrationFrame extends JFrame {

    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationFrame() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Register Button
        registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(registerButton, gbc);

        // Action
        registerButton.addActionListener(e -> registerAction());

        pack();
        setVisible(true);
    }

    private void registerAction() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        Registration reg = new Registration(name, email, password);
        RegistrationDAO dao = new RegistrationDAO();

        if (dao.addRegistration(reg)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            dispose(); // close registration
            new LoginFrame(); // go back to login
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed! Email might already exist.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationFrame());
    }
}
    */


    package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import dao.RegistrationDAO;
import model.Registration;

public class RegistrationFrame extends JFrame {

    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationFrame() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(true);

        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // light gray background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Title
        JLabel title = new JLabel("Register New Account", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(33, 150, 243)); // blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1; // reset

        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(nameLabel, gbc);

        // Name Field
        nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(nameField, gbc);

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(emailLabel, gbc);

        // Email Field
        emailField = new JTextField(20);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(emailField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(passwordLabel, gbc);

        // Password Field
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(passwordField, gbc);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(33, 150, 243));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(registerButton, gbc);

        // Action
        registerButton.addActionListener(e -> registerAction());

        add(mainPanel);
        setVisible(true);
    }

    private void registerAction() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        Registration reg = new Registration(name, email, password);
        RegistrationDAO dao = new RegistrationDAO();

        if (dao.addRegistration(reg)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!");
            dispose(); // close registration
            new LoginFrame(); // go back to login
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed! Email might already exist.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationFrame());
    }
}