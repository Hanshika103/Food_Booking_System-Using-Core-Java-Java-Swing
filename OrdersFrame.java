

   /*  package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import dao.BookingDAO;
import model.Order;

public class OrdersFrame extends JFrame {

    private JTable ordersTable;
    private JButton backButton;
    private String userEmail;
    private DefaultTableModel model;

    public OrdersFrame(String email) {

        this.userEmail = email;

        if(this.userEmail == null || this.userEmail.isEmpty()){
            JOptionPane.showMessageDialog(this,"User session error. Please login again.");
            dispose();
            new LoginFrame();
            return;
        }

        initUI();
    }

    private void initUI(){

        setTitle("FoodBooking - My Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700,420);
        setResizable(true);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBackground(new Color(245,245,245));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel title = new JLabel("My Orders", JLabel.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,22));
        title.setForeground(new Color(33,150,243));

        panel.add(title,BorderLayout.NORTH);

        // Table Columns
        String[] columns = {
                "Order ID",
                "Items & Quantity",
                "Total Price (₹)",
                "Status",
                "Order Date"
        };

        model = new DefaultTableModel(columns,0){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        ordersTable = new JTable(model);

        ordersTable.setFont(new Font("Segoe UI",Font.PLAIN,14));
        ordersTable.setRowHeight(25);

        ordersTable.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
        ordersTable.getTableHeader().setBackground(new Color(33,150,243));
        ordersTable.getTableHeader().setForeground(Color.WHITE);

        // Alternate row colors
        ordersTable.setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){

            public Component getTableCellRendererComponent(
                    JTable table,Object value,boolean isSelected,
                    boolean hasFocus,int row,int column){

                Component c = super.getTableCellRendererComponent(
                        table,value,isSelected,hasFocus,row,column);

                if(!isSelected){
                    c.setBackground(row % 2 == 0 ?
                            Color.WHITE : new Color(230,230,250));
                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        panel.add(scrollPane,BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back to Menu");

        backButton.setBackground(new Color(244,67,54));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI",Font.BOLD,16));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        backButton.addActionListener(e -> goBack());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(245,245,245));
        southPanel.add(backButton);

        panel.add(southPanel,BorderLayout.SOUTH);

        add(panel);

        loadOrders();

        setVisible(true);
    }

    private void loadOrders(){

        model.setRowCount(0); // Clear old rows

        BookingDAO dao = new BookingDAO();

        List<Order> orders = dao.getOrdersForUser(userEmail);

        for(Order order : orders){

            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getItems(),
                    order.getTotalPrice(),
                    order.getStatus(),
                    order.getOrderDate()
            });
        }

        if(orders.isEmpty()){
            JOptionPane.showMessageDialog(this,"No orders found.");
        }
    }

    private void goBack(){

        dispose();

        new MenuFrame(userEmail);
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(
                () -> new OrdersFrame("test@example.com"));
    }
}*/

package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import dao.BookingDAO;
import model.Order;

public class OrdersFrame extends JFrame {

    private JTable ordersTable;
    private JButton backButton;
    private int studentId; // ✅ FIXED
    private DefaultTableModel model;

    public OrdersFrame(int studentId) {

        this.studentId = studentId;

        if (this.studentId <= 0) {
            JOptionPane.showMessageDialog(this,"User session error. Please login again.");
            dispose();
            new LoginFrame();
            return;
        }

        initUI();
    }

    private void initUI(){

        setTitle("FoodBooking - My Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700,420);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBackground(new Color(245,245,245));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel title = new JLabel("My Orders", JLabel.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,22));

        panel.add(title,BorderLayout.NORTH);

        // Table Columns
        String[] columns = {
                "Order ID",
                "Total Amount (₹)",
                "Status",
                "Order Date"
        };

        model = new DefaultTableModel(columns,0){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        ordersTable = new JTable(model);

        ordersTable.setRowHeight(25);

        // Alternate row colors
        ordersTable.setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(
                    JTable table,Object value,boolean isSelected,
                    boolean hasFocus,int row,int column){

                Component c = super.getTableCellRendererComponent(
                        table,value,isSelected,hasFocus,row,column);

                if(!isSelected){
                    c.setBackground(row % 2 == 0 ?
                            Color.WHITE : new Color(230,230,250));
                }

                return c;
            }
        });

        panel.add(new JScrollPane(ordersTable),BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> goBack());

        JPanel southPanel = new JPanel();
        southPanel.add(backButton);

        panel.add(southPanel,BorderLayout.SOUTH);

        add(panel);

        loadOrders();

        setVisible(true);
    }

    // ==============================
    // 🔥 FIXED METHOD
    // ==============================
    private void loadOrders(){

        model.setRowCount(0);

        BookingDAO dao = new BookingDAO();

        List<Order> orders = dao.getOrdersForUser(studentId); // ✅ FIXED

        for(Order order : orders){

            model.addRow(new Object[]{
                    order.getOrderId(),
                    order.getTotalAmount(), // ✅ FIXED
                    order.getStatus(),
                    order.getOrderDate()
            });
        }

        if(orders.isEmpty()){
            JOptionPane.showMessageDialog(this,"No orders found.");
        }
    }

    private void goBack(){

        dispose();
        new MenuFrame(studentId, "User"); // ✅ FIXED
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(
                () -> new OrdersFrame(1));
    }
}