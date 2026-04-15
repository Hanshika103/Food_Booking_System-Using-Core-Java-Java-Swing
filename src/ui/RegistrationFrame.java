package ui; // Package name (UI layer folder)

import javax.swing.*; // Swing components (JFrame, JTextField, JButton, etc.)
import java.awt.*; // Layout, colors, fonts
import javax.swing.border.LineBorder; // For rounded borders on fields

import dao.RegistrationDAO; // DAO class (handles database operations for registration)
import model.Registration; // Model class (stores user data)

// Registration screen for new users
public class RegistrationFrame extends JFrame {

    // UI components
    private JTextField nameField, emailField; // input fields for name and email
    private JPasswordField passwordField; // password input field
    private JButton registerButton; // button to submit registration

    // Constructor (runs when frame is created)
    public RegistrationFrame() {

        setTitle("Registration Form"); // window title

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        // close only this window, not full application

        setSize(450, 350); // window size

        setLocationRelativeTo(null); // center screen

        setResizable(true); // allow resizing

        // ==============================
        // MAIN PANEL
        // ==============================
        JPanel mainPanel = new JPanel(new GridBagLayout()); // layout manager

        mainPanel.setBackground(new Color(245, 245, 245)); // light gray background

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        // padding around panel

        GridBagConstraints gbc = new GridBagConstraints(); // layout rules

        gbc.insets = new Insets(15, 15, 15, 15); // spacing between components

        gbc.fill = GridBagConstraints.HORIZONTAL; // stretch horizontally

        gbc.weightx = 1; // allow resizing horizontally

        // ==============================
        // TITLE LABEL
        // ==============================
        JLabel title = new JLabel("Register New Account", JLabel.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 20)); // font style

        title.setForeground(new Color(33, 150, 243)); // blue color

        gbc.gridx = 0; // column
        gbc.gridy = 0; // row
        gbc.gridwidth = 2; // span 2 columns

        mainPanel.add(title, gbc); // add title

        gbc.gridwidth = 1; // reset grid width

        // ==============================
        // NAME LABEL
        // ==============================
        JLabel nameLabel = new JLabel("Name:");

        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // font

        gbc.gridx = 0;
        gbc.gridy = 1;

        mainPanel.add(nameLabel, gbc); // add label

        // Name field
        nameField = new JTextField(20); // text box

        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        nameField.setBorder(new LineBorder(Color.GRAY, 1, true)); // rounded border

        gbc.gridx = 1;
        gbc.gridy = 1;

        mainPanel.add(nameField, gbc); // add field

        // ==============================
        // EMAIL LABEL
        // ==============================
        JLabel emailLabel = new JLabel("Email:");

        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 2;

        mainPanel.add(emailLabel, gbc);

        // Email field
        emailField = new JTextField(20);

        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        emailField.setBorder(new LineBorder(Color.GRAY, 1, true));

        gbc.gridx = 1;
        gbc.gridy = 2;

        mainPanel.add(emailField, gbc);

        // ==============================
        // PASSWORD LABEL
        // ==============================
        JLabel passwordLabel = new JLabel("Password:");

        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 3;

        mainPanel.add(passwordLabel, gbc);

        // Password field
        passwordField = new JPasswordField(20);

        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        passwordField.setBorder(new LineBorder(Color.GRAY, 1, true));

        gbc.gridx = 1;
        gbc.gridy = 3;

        mainPanel.add(passwordField, gbc);

        // ==============================
        // REGISTER BUTTON
        // ==============================
        registerButton = new JButton("Register");

        registerButton.setBackground(new Color(33, 150, 243)); // blue

        registerButton.setForeground(Color.WHITE); // white text

        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));

        registerButton.setFocusPainted(false); // remove focus border

        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        mainPanel.add(registerButton, gbc);

        // Button click action
        registerButton.addActionListener(e -> registerAction());

        add(mainPanel); // add panel to frame

        setVisible(true); // show window
    }

    // ==============================
    // REGISTRATION LOGIC
    // ==============================
    private void registerAction() {

        // get user inputs
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        // validation check
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return; // stop execution
        }

        // create model object
        Registration reg = new Registration(name, email, password);

        // DAO object
        RegistrationDAO dao = new RegistrationDAO();

        // insert into database
        if (dao.addRegistration(reg)) {

            JOptionPane.showMessageDialog(this, "Registration Successful!");

            dispose(); // close registration window

            new LoginFrame(); // go back to login screen

        } else {

            JOptionPane.showMessageDialog(this,
                    "Registration Failed! Email might already exist.");
        }
    }

    // Main method (program starts here)
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new RegistrationFrame());
        // safe UI thread execution
    }
}
