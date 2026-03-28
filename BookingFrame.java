

/*package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import dao.BookingDAO;
import dao.MenuDAO;
import model.Order;
import model.MenuItem;

public class BookingFrame extends JFrame {

    private List<JCheckBox> foodCheckboxes;
    private List<JSpinner> quantitySpinners;
    private JButton placeOrderButton, backButton;

    private String userEmail;

    private List<MenuItem> menuItems; // menu from DB

    public BookingFrame(String email) {
        this.userEmail = email;
        initUI();
    }

    private void initUI() {

        setTitle("Book Food");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
        setResizable(true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245,245,245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Select Food Items");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        foodCheckboxes = new ArrayList<>();
        quantitySpinners = new ArrayList<>();

        // Load menu from DB
        MenuDAO menuDAO = new MenuDAO();
        menuItems = menuDAO.getAllItems();

        int row = 1;

        for (MenuItem item : menuItems) {

            JCheckBox cb = new JCheckBox(item.getItemName() + " (₹" + item.getPrice() + ")");
            cb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            foodCheckboxes.add(cb);

            gbc.gridx = 0;
            gbc.gridy = row;
            panel.add(cb, gbc);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
            quantitySpinners.add(spinner);

            gbc.gridx = 1;
            panel.add(spinner, gbc);

            row++;
        }

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBackground(new Color(33,150,243));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.setFont(new Font("Segoe UI", Font.BOLD, 16));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(placeOrderButton, gbc);

        row++;

        backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(244,67,54));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));

        gbc.gridy = row;
        panel.add(backButton, gbc);

        add(panel);

        placeOrderButton.addActionListener(e -> placeOrder());
        backButton.addActionListener(e -> goBack());

        setVisible(true);
    }

    private void placeOrder() {

        List<String> selectedItems = new ArrayList<>();
        int totalPrice = 0;

        for (int i = 0; i < foodCheckboxes.size(); i++) {

            JCheckBox cb = foodCheckboxes.get(i);

            if (cb.isSelected()) {

                int qty = (Integer) quantitySpinners.get(i).getValue();
                MenuItem item = menuItems.get(i);

                int price = (int) item.getPrice() * qty;

                totalPrice += price;

                selectedItems.add(item.getItemName() + " x" + qty + " (₹" + price + ")");
            }
        }

        if (selectedItems.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please select at least one item.");
            return;
        }

       Order order = new Order(
        userEmail,
        String.join(", ", selectedItems),
        totalPrice,
        new java.util.Date(),
        false
);

        BookingDAO dao = new BookingDAO();

        if (dao.placeOrder(order)) {

            JOptionPane.showMessageDialog(this,
                    "Order placed successfully!\nTotal: ₹" + totalPrice);

        } else {

            JOptionPane.showMessageDialog(this,
                    "Failed to place order.");
        }
    }

    private void goBack() {

        dispose();
        new MenuFrame(userEmail);
    }
}
   */ 


  package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import dao.BookingDAO;
import dao.MenuDAO;
import model.Order;
import model.MenuItem;

public class BookingFrame extends JFrame {

    private List<JCheckBox> foodCheckboxes;
    private List<JSpinner> quantitySpinners;
    private JButton placeOrderButton, backButton;

    private int studentId;
    private String userName; // ✅ added

    private List<MenuItem> menuItems;

    // ✅ FINAL CONSTRUCTOR
    public BookingFrame(int studentId, String userName) {
        this.studentId = studentId;
        this.userName = userName;
        initUI();
    }

    private void initUI() {

        setTitle("Book Food");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245,245,245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Select Food Items");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        foodCheckboxes = new ArrayList<>();
        quantitySpinners = new ArrayList<>();

        // Load menu from DB
        MenuDAO menuDAO = new MenuDAO();
        menuItems = menuDAO.getAllItems();

        int row = 1;

        for (MenuItem item : menuItems) {

            JCheckBox cb = new JCheckBox(item.getItemName() + " (₹" + item.getPrice() + ")");
            foodCheckboxes.add(cb);

            gbc.gridx = 0;
            gbc.gridy = row;
            panel.add(cb, gbc);

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,1,10,1));
            quantitySpinners.add(spinner);

            gbc.gridx = 1;
            panel.add(spinner, gbc);

            row++;
        }

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBackground(new Color(33,150,243));
        placeOrderButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(placeOrderButton, gbc);

        row++;

        backButton = new JButton("Back to Menu");
        backButton.setBackground(new Color(244,67,54));
        backButton.setForeground(Color.WHITE);

        gbc.gridy = row;
        panel.add(backButton, gbc);

        add(panel);

        placeOrderButton.addActionListener(e -> placeOrder());
        backButton.addActionListener(e -> goBack());

        setVisible(true);
    }

    // ==============================
    // 🔥 FINAL PLACE ORDER METHOD
    // ==============================
    private void placeOrder() {

        List<Order.Item> itemsList = new ArrayList<>();
        double totalAmount = 0;

        for (int i = 0; i < foodCheckboxes.size(); i++) {

            JCheckBox cb = foodCheckboxes.get(i);

            if (cb.isSelected()) {

                int qty = (Integer) quantitySpinners.get(i).getValue();
                MenuItem item = menuItems.get(i);

                double subtotal = item.getPrice() * qty;
                totalAmount += subtotal;

                itemsList.add(new Order.Item(
                        item.getItemId(),
                        qty,
                        subtotal
                ));
            }
        }

        if (itemsList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one item.");
            return;
        }

        Order order = new Order(
                studentId,
                new java.util.Date(),
                totalAmount,
                "Preparing",
                itemsList
        );

        BookingDAO dao = new BookingDAO();

        if (dao.placeOrder(order)) {

            JOptionPane.showMessageDialog(this,
                    "Order placed successfully!\nTotal: ₹" + totalAmount);

            // ✅ OPTIONAL: clear selection after order
            for (JCheckBox cb : foodCheckboxes) {
                cb.setSelected(false);
            }

        } else {

            JOptionPane.showMessageDialog(this,
                    "Failed to place order.");
        }
    }

    // ==============================
    // 🔥 FINAL BACK METHOD
    // ==============================
    private void goBack() {
        dispose();
        new MenuFrame(studentId, userName); // ✅ FIXED
    }
}