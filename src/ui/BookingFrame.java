package ui; // Package name (folder where this class belongs)

import javax.swing.*; // Swing UI components
import java.awt.*; // Layouts, colors, fonts
import java.util.ArrayList; // Dynamic list
import java.util.List; // List interface

import dao.BookingDAO; // Handles database operations for orders
import dao.MenuDAO; // Fetches menu items from database
import model.Order; // Order model class
import model.MenuItem; // Menu item model class

// Frame where student/user selects food and places order
public class BookingFrame extends JFrame {

    private List<JCheckBox> foodCheckboxes; // Checkbox list for menu items
    private List<JSpinner> quantitySpinners; // Quantity selectors
    private JButton placeOrderButton, backButton; // Action buttons

    private int studentId; // Logged-in student ID
    private String userName; // Logged-in username

    private List<MenuItem> menuItems; // Menu items from database

    // Constructor (receives user info from previous screen)
    public BookingFrame(int studentId, String userName) {
        this.studentId = studentId; // store student ID
        this.userName = userName; // store username
        initUI(); // build UI
    }

    // ==============================
    // CREATE UI
    // ==============================
    private void initUI() {

        setTitle("Book Food"); // Window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program on close

        setLocationRelativeTo(null); // Center window

        setSize(500, 400); // Window size

        // Main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(new Color(245,245,245)); // Light gray background

        GridBagConstraints gbc = new GridBagConstraints(); // Layout rules

        gbc.insets = new Insets(10,10,10,10); // spacing between components

        gbc.fill = GridBagConstraints.HORIZONTAL; // stretch horizontally

        // Title label
        JLabel title = new JLabel("Select Food Items");

        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        title.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc); // add title to panel

        foodCheckboxes = new ArrayList<>(); // initialize checkbox list
        quantitySpinners = new ArrayList<>(); // initialize spinner list

        // Load menu items from database
        MenuDAO menuDAO = new MenuDAO();
        menuItems = menuDAO.getAllItems();

        int row = 1; // start from row 1

        // Loop through menu items
        for (MenuItem item : menuItems) {

            // Create checkbox for item
            JCheckBox cb = new JCheckBox(item.getItemName() + " (₹" + item.getPrice() + ")");
            foodCheckboxes.add(cb); // store checkbox

            gbc.gridx = 0;
            gbc.gridy = row;
            panel.add(cb, gbc); // add checkbox

            // Quantity selector (1 to 10)
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
            quantitySpinners.add(spinner);

            gbc.gridx = 1;
            panel.add(spinner, gbc); // add spinner

            row++; // move to next row
        }

        // ==============================
        // PLACE ORDER BUTTON
        // ==============================
        placeOrderButton = new JButton("Place Order");

        placeOrderButton.setBackground(new Color(33,150,243));

        placeOrderButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;

        panel.add(placeOrderButton, gbc);

        row++;

        // ==============================
        // BACK BUTTON
        // ==============================
        backButton = new JButton("Back to Menu");

        backButton.setBackground(new Color(244,67,54));

        backButton.setForeground(Color.WHITE);

        gbc.gridy = row;

        panel.add(backButton, gbc);

        add(panel); // add panel to frame

        // Button actions
        placeOrderButton.addActionListener(e -> placeOrder());
        backButton.addActionListener(e -> goBack());

        setVisible(true); // show window
    }

    // ==============================
    // PLACE ORDER LOGIC
    // ==============================
    private void placeOrder() {

        List<Order.Item> itemsList = new ArrayList<>(); // selected items
        double totalAmount = 0; // total bill

        // Loop through all menu items
        for (int i = 0; i < foodCheckboxes.size(); i++) {

            JCheckBox cb = foodCheckboxes.get(i);

            // If item is selected
            if (cb.isSelected()) {

                int qty = (Integer) quantitySpinners.get(i).getValue(); // quantity

                MenuItem item = menuItems.get(i); // actual item

                double subtotal = item.getPrice() * qty; // item total

                totalAmount += subtotal; // add to total

                // Add item to order list
                itemsList.add(new Order.Item(
                        item.getItemId(),
                        qty,
                        subtotal
                ));
            }
        }

        // If nothing selected
        if (itemsList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one item.");
            return;
        }

        // Create Order object
        Order order = new Order(
                studentId,
                new java.util.Date(),
                totalAmount,
                "Preparing",
                itemsList
        );

        BookingDAO dao = new BookingDAO(); // DAO object

        // Save order to database
        if (dao.placeOrder(order)) {

            JOptionPane.showMessageDialog(this,
                    "Order placed successfully!\nTotal: ₹" + totalAmount);

            // Clear selections after success
            for (JCheckBox cb : foodCheckboxes) {
                cb.setSelected(false);
            }

        } else {

            JOptionPane.showMessageDialog(this,
                    "Failed to place order.");
        }
    }

    // ==============================
    // GO BACK TO MENU
    // ==============================
    private void goBack() {

        dispose(); // close current window

        new MenuFrame(studentId, userName); // open menu screen
    }
}
