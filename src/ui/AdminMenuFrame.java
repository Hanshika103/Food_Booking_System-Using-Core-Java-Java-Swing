package ui; // Package name

import javax.swing.*; // Swing components (JFrame, JButton, etc.)
import javax.swing.table.DefaultTableModel; // Table model for JTable
import java.awt.*; // Layouts and UI utilities
import java.util.List; // List collection

import dao.MenuDAO; // DAO class for database operations
import model.MenuItem; // Model class representing menu item

// Frame for Admin to manage menu
public class AdminMenuFrame extends JFrame {

    JTable table; // Table to display menu items
    JTextField nameField, categoryField, priceField; // Input fields

    // Constructor
    public AdminMenuFrame() {

        setTitle("Admin - Manage Menu"); // Set window title
        setSize(600,400); // Set window size
        setLocationRelativeTo(null); // Center window on screen

        // Main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Column names for table
        String[] columns = {"ID","Item","Category","Price","Status"};

        // Create table model (0 rows initially)
        DefaultTableModel model = new DefaultTableModel(columns,0);

        table = new JTable(model); // Create table using model

        loadMenu(model); // Load data from database into table

        // Add table inside scroll pane (for scrolling)
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom panel for inputs and buttons
        JPanel bottom = new JPanel();

        // Create input fields
        nameField = new JTextField(10);
        categoryField = new JTextField(10);
        priceField = new JTextField(6);

        // Create buttons
        JButton addBtn = new JButton("Add Item");
        JButton deleteBtn = new JButton("Delete Item");
        JButton refreshBtn = new JButton("Refresh");

        // Add components to bottom panel
        bottom.add(new JLabel("Name"));
        bottom.add(nameField);

        bottom.add(new JLabel("Category"));
        bottom.add(categoryField);

        bottom.add(new JLabel("Price"));
        bottom.add(priceField);

        bottom.add(addBtn);
        bottom.add(deleteBtn);
        bottom.add(refreshBtn);

        // Add bottom panel to frame (south position)
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel); // Add main panel to frame

        // Button actions
        addBtn.addActionListener(e -> addItem());       // Add item
        deleteBtn.addActionListener(e -> deleteItem()); // Delete item
        refreshBtn.addActionListener(e -> refresh());   // Refresh table

        setVisible(true); // Make frame visible
    }

    // ============================
    // Load menu items into table
    // ============================
    private void loadMenu(DefaultTableModel model) {

        MenuDAO dao = new MenuDAO(); // Create DAO object

        List<MenuItem> items = dao.getAllItems(); 
        // Get all menu items from database

        // Loop through items and add rows to table
        for(MenuItem m : items){

            model.addRow(new Object[]{
                    m.getItemId(),           // ID
                    m.getItemName(),         // Name
                    m.getCategory(),         // Category
                    m.getPrice(),            // Price
                    m.getAvailabilityStatus() // Status
            });
        }
    }

    // ============================
    // Add new menu item
    // ============================
    private void addItem(){

        String name = nameField.getText(); // Get item name
        String category = categoryField.getText(); // Get category

        double price = Double.parseDouble(priceField.getText()); 
        // Convert price from text to double

        MenuDAO dao = new MenuDAO(); // DAO object

        // Create new MenuItem object
        MenuItem item = new MenuItem(name,category,price,"Available");

        // Add item to database
        if(dao.addItem(item)){

            JOptionPane.showMessageDialog(this,"Item Added Successfully");

            refresh(); // Refresh UI
        }
    }

    // ============================
    // Delete selected item
    // ============================
    private void deleteItem(){

        int row = table.getSelectedRow(); 
        // Get selected row index

        if(row == -1){ // If no row selected

            JOptionPane.showMessageDialog(this,"Select item first");
            return;
        }

        int id = (int) table.getValueAt(row,0); 
        // Get ID from first column

        MenuDAO dao = new MenuDAO(); // DAO object

        // Delete item from database
        if(dao.deleteItem(id)){

            JOptionPane.showMessageDialog(this,"Item Deleted");

            refresh(); // Refresh UI
        }
    }

    // ============================
    // Refresh screen
    // ============================
    private void refresh(){

        dispose(); // Close current window

        new AdminMenuFrame(); 
        // Reopen frame (reload updated data)
    }
}
