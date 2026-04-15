package ui; // Package name (folder where this class belongs)

import javax.swing.*; // Swing components (JFrame, JTable, JButton, etc.)
import javax.swing.table.DefaultTableCellRenderer; // For custom table row styling
import javax.swing.table.DefaultTableModel; // For table data handling
import java.awt.*; // Layout, colors, fonts
import java.util.List; // List collection

import dao.BookingDAO; // DAO class for fetching orders from database
import model.Order; // Order model class

// Frame to show logged-in user's orders
public class OrdersFrame extends JFrame {

    private JTable ordersTable; // Table to display orders
    private JButton backButton; // Back navigation button
    private int studentId; // Logged-in user ID
    private DefaultTableModel model; // Table model

    // Constructor (receives user ID from MenuFrame)
    public OrdersFrame(int studentId) {

        this.studentId = studentId; // store user ID

        // Validate session
        if (this.studentId <= 0) {
            JOptionPane.showMessageDialog(this,"User session error. Please login again.");
            dispose(); // close window
            new LoginFrame(); // go back to login
            return; // stop execution
        }

        initUI(); // build UI
    }

    // ==============================
    // CREATE UI
    // ==============================
    private void initUI(){

        setTitle("FoodBooking - My Orders"); // window title

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app on exit

        setLocationRelativeTo(null); // center window

        setSize(700,420); // window size

        // Main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout(10,10));

        panel.setBackground(new Color(245,245,245)); // light background

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20)); // padding

        // Title label
        JLabel title = new JLabel("My Orders", JLabel.CENTER);

        title.setFont(new Font("Segoe UI",Font.BOLD,22)); // font style

        panel.add(title,BorderLayout.NORTH); // add title at top

        // ==============================
        // TABLE COLUMNS
        // ==============================
        String[] columns = {
                "Order ID",
                "Total Amount (₹)",
                "Status",
                "Order Date"
        };

        // Table model (non-editable)
        model = new DefaultTableModel(columns,0){
            public boolean isCellEditable(int row,int column){
                return false; // disable editing
            }
        };

        ordersTable = new JTable(model); // create table

        ordersTable.setRowHeight(25); // row height

        // ==============================
        // ROW COLOR STYLING
        // ==============================
        ordersTable.setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){

            public Component getTableCellRendererComponent(
                    JTable table,Object value,boolean isSelected,
                    boolean hasFocus,int row,int column){

                Component c = super.getTableCellRendererComponent(
                        table,value,isSelected,hasFocus,row,column);

                // Alternate row colors (zebra effect)
                if(!isSelected){
                    c.setBackground(row % 2 == 0 ?
                            Color.WHITE : new Color(230,230,250));
                }

                return c;
            }
        });

        // Add table inside scroll pane
        panel.add(new JScrollPane(ordersTable),BorderLayout.CENTER);

        // ==============================
        // BACK BUTTON
        // ==============================
        backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> goBack()); // click action

        JPanel southPanel = new JPanel(); // bottom panel

        southPanel.add(backButton); // add button

        panel.add(southPanel,BorderLayout.SOUTH); // attach bottom panel

        add(panel); // add main panel to frame

        loadOrders(); // fetch data from database

        setVisible(true); // show window
    }

    // ==============================
    // LOAD ORDERS FROM DATABASE
    // ==============================
    private void loadOrders(){

        model.setRowCount(0); // clear previous data

        BookingDAO dao = new BookingDAO(); // DAO object

        // fetch orders for logged-in user
        List<Order> orders = dao.getOrdersForUser(studentId);

        // add each order into table
        for(Order order : orders){

            model.addRow(new Object[]{
                    order.getOrderId(),       // order ID
                    order.getTotalAmount(),   // total amount
                    order.getStatus(),        // order status
                    order.getOrderDate()      // order date
            });
        }

        // if no orders found
        if(orders.isEmpty()){
            JOptionPane.showMessageDialog(this,"No orders found.");
        }
    }

    // ==============================
    // GO BACK TO MENU
    // ==============================
    private void goBack(){

        dispose(); // close current window

        new MenuFrame(studentId, "User"); // go back to menu
    }

    // Main method (program starts here)
    public static void main(String[] args){

        SwingUtilities.invokeLater(
                () -> new OrdersFrame(1)); // test run
    }
}
