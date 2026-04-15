package ui; // Package name (folder where this class belongs)

import javax.swing.*; // Import Swing components (JFrame, JButton, JTextField, etc.)
import java.awt.*; // Import AWT (layout, colors, fonts)
import javax.swing.border.LineBorder; // For adding border to text fields

import dao.LoginDAO; // DAO class for database login validation
import model.Registration; // Model class (represents user data)

// Login screen for user
public class LoginFrame extends JFrame {

    // UI components declaration
    private JTextField emailField; // Input field for email
    private JPasswordField passwordField; // Input field for password (hidden text)
    private JButton loginButton, registerButton; // Buttons for login and registration navigation

    // Constructor (runs when object is created)
    public LoginFrame() {

        setTitle("User Login"); // Set window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program when window is closed

        setSize(420,300); // Set window size (width, height)

        setLocationRelativeTo(null); // Center window on screen

        setResizable(false); // Prevent resizing window

        // Main panel using GridBagLayout (flexible layout)
        JPanel mainPanel = new JPanel(new GridBagLayout());

        mainPanel.setBackground(new Color(245,245,245)); // Light gray background

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20)); 
        // Add padding around panel

        GridBagConstraints gbc = new GridBagConstraints(); // Layout constraints object

        gbc.insets = new Insets(12,12,12,12); // Space between components

        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally

        // ==============================
        // TITLE LABEL
        // ==============================
        JLabel title = new JLabel("Login to Your Account", JLabel.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Font style

        title.setForeground(new Color(33,150,243)); // Blue color

        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.gridwidth = 2; // Span 2 columns

        mainPanel.add(title, gbc); // Add title to panel

        gbc.gridwidth = 1; // Reset to default

        // ==============================
        // EMAIL LABEL
        // ==============================
        JLabel emailLabel = new JLabel("Email:");

        gbc.gridx = 0;
        gbc.gridy = 1;

        mainPanel.add(emailLabel, gbc); // Add email label

        // Email input field
        emailField = new JTextField(20);

        emailField.setBorder(new LineBorder(Color.GRAY,1,true)); // Rounded border

        gbc.gridx = 1;

        mainPanel.add(emailField, gbc); // Add email field

        // ==============================
        // PASSWORD LABEL
        // ==============================
        JLabel passwordLabel = new JLabel("Password:");

        gbc.gridx = 0;
        gbc.gridy = 2;

        mainPanel.add(passwordLabel, gbc); // Add password label

        // Password input field
        passwordField = new JPasswordField(20);

        passwordField.setBorder(new LineBorder(Color.GRAY,1,true)); // Border

        gbc.gridx = 1;

        mainPanel.add(passwordField, gbc); // Add password field

        // ==============================
        // LOGIN BUTTON
        // ==============================
        loginButton = new JButton("Login");

        loginButton.setBackground(new Color(33,150,243)); // Blue background

        loginButton.setForeground(Color.WHITE); // White text

        loginButton.setFocusPainted(false); // Remove focus border

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        mainPanel.add(loginButton, gbc); // Add login button

        // ==============================
        // REGISTER BUTTON
        // ==============================
        registerButton = new JButton("Go to Register");

        registerButton.setBackground(new Color(76,175,80)); // Green background

        registerButton.setForeground(Color.WHITE); // White text

        registerButton.setFocusPainted(false); // Remove focus border

        gbc.gridy = 4;

        mainPanel.add(registerButton, gbc); // Add register button

        add(mainPanel); // Add panel to frame

        // Button click actions
        loginButton.addActionListener(e -> loginAction()); // Login event
        registerButton.addActionListener(e -> openRegistration()); // Register event

        setVisible(true); // Show window
    }

    // ==============================
    // LOGIN LOGIC METHOD
    // ==============================
    private void loginAction() {

        String email = emailField.getText().trim(); // Get email input

        String password = new String(passwordField.getPassword()).trim(); 
        // Get password input

        // Check empty fields
        if(email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please enter email and password");

            return; // Stop execution
        }

        LoginDAO dao = new LoginDAO(); // Create DAO object

        // Call DAO method to validate user in database
        Registration user = dao.validate(
                new Registration("", email, password)
        );

        // If user exists
        if(user != null) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");

            dispose(); // Close login window

            // Open menu frame and pass user data
            new MenuFrame(user.getRegId(), user.getName());

        } else {

            // If login fails
            JOptionPane.showMessageDialog(this,
                    "Invalid Email or Password");
        }
    }

    // ==============================
    // OPEN REGISTRATION SCREEN
    // ==============================
    private void openRegistration() {

        dispose(); // Close current window

        new RegistrationFrame(); // Open registration screen
    }

    // Main method (program starts here)
    public static void main(String[] args) {

        SwingUtilities.invokeLater(LoginFrame::new); 
        // Run UI safely on Event Dispatch Thread
    }
}
