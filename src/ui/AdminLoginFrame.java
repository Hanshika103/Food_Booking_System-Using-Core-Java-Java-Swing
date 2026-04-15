package ui; // Package name (folder where this class is stored)

import javax.swing.*; // Import all Swing classes (JFrame, JButton, etc.)
import java.awt.*;    // Import AWT classes (Layout, Color, Font, etc.)

import dao.AdminDAO; // Import AdminDAO class for database validation

// This class creates a login window for admin
public class AdminLoginFrame extends JFrame {

    // Declare UI components
    private JTextField usernameField;     // Text field for username input
    private JPasswordField passwordField; // Password field (hidden input)
    private JButton loginButton, backButton; // Buttons for login and back

    // Constructor (called when object is created)
    public AdminLoginFrame() {
        initUI(); // Call custom method to build UI
    }

    // Method to design UI
    private void initUI() {

        setTitle("Admin Login"); // Set window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // Close application when window is closed

        setSize(400, 260); // Set window size (width, height)

        setLocationRelativeTo(null); 
        // Center the window on screen

        // Create panel with GridBagLayout (flexible layout system)
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(new Color(245, 245, 245)); 
        // Set background color (light gray)

        // Create constraints object to control layout
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(12, 12, 12, 12); 
        // Add padding (top, left, bottom, right)

        gbc.fill = GridBagConstraints.HORIZONTAL; 
        // Components will stretch horizontally

        // Create title label
        JLabel title = new JLabel("Admin Login", JLabel.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 20)); 
        // Set font style and size

        gbc.gridx = 0; // Column position
        gbc.gridy = 0; // Row position
        gbc.gridwidth = 2; // Span across 2 columns

        panel.add(title, gbc); // Add title to panel

        gbc.gridwidth = 1; // Reset to 1 column

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        // Username text field
        usernameField = new JTextField(15); // 15 columns width
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        // Password field
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across 2 columns
        panel.add(loginButton, gbc);

        // Back button
        backButton = new JButton("Back");
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        add(panel); // Add panel to frame

        // Add action listener for login button
        loginButton.addActionListener(e -> adminLogin());

        // Add action listener for back button
        backButton.addActionListener(e -> goBack());

        setVisible(true); // Make frame visible
    }

    // ==============================
    // LOGIN FUNCTION
    // ==============================
    private void adminLogin() {

        String username = usernameField.getText().trim(); 
        // Get username and remove extra spaces

        String password = new String(passwordField.getPassword()).trim(); 
        // Get password safely and trim spaces

        AdminDAO dao = new AdminDAO(); 
        // Create DAO object for database check

        // Check if username and password are valid
        if (dao.validateAdmin(username, password)) {

            JOptionPane.showMessageDialog(this, "Admin Login Successful");
            // Show success message

            dispose(); // Close current window

            new AdminOrdersFrame(); 
            // Open admin dashboard (next screen)

        } else {

            JOptionPane.showMessageDialog(this, "Invalid credentials");
            // Show error message
        }
    }

    // Back button function
    private void goBack() {

        dispose(); // Close current window

        new LoginFrame(); 
        // Open previous login screen
    }

    // Main method (program starts here)
    public static void main(String[] args) {

        SwingUtilities.invokeLater(AdminLoginFrame::new);
        // Run UI safely on Event Dispatch Thread
    }
}
