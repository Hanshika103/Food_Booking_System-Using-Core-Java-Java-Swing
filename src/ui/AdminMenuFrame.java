package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import dao.MenuDAO;
import model.MenuItem;

public class AdminMenuFrame extends JFrame {

    JTable table;
    JTextField nameField, categoryField, priceField;

    

    public AdminMenuFrame() {

        setTitle("Admin - Manage Menu");
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"ID","Item","Category","Price","Status"};

        DefaultTableModel model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        loadMenu(model);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        nameField = new JTextField(10);
        categoryField = new JTextField(10);
        priceField = new JTextField(6);

        JButton addBtn = new JButton("Add Item");
        JButton deleteBtn = new JButton("Delete Item");
        JButton refreshBtn = new JButton("Refresh");

        bottom.add(new JLabel("Name"));
        bottom.add(nameField);

        bottom.add(new JLabel("Category"));
        bottom.add(categoryField);

        bottom.add(new JLabel("Price"));
        bottom.add(priceField);

        bottom.add(addBtn);
        bottom.add(deleteBtn);
        bottom.add(refreshBtn);

        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);

        addBtn.addActionListener(e -> addItem());
        deleteBtn.addActionListener(e -> deleteItem());
        refreshBtn.addActionListener(e -> refresh());

        setVisible(true);
    }

    private void loadMenu(DefaultTableModel model) {

        MenuDAO dao = new MenuDAO();

        List<MenuItem> items = dao.getAllItems();

        for(MenuItem m : items){

            model.addRow(new Object[]{
                    m.getItemId(),
                    m.getItemName(),
                    m.getCategory(),
                    m.getPrice(),
                    m.getAvailabilityStatus()
            });
        }
    }

    private void addItem(){

        String name = nameField.getText();
        String category = categoryField.getText();
        double price = Double.parseDouble(priceField.getText());

        MenuDAO dao = new MenuDAO();

        MenuItem item = new MenuItem(name,category,price,"Available");

        if(dao.addItem(item)){

            JOptionPane.showMessageDialog(this,"Item Added Successfully");

            refresh();
        }
    }

    private void deleteItem(){

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(this,"Select item first");
            return;
        }

        int id = (int) table.getValueAt(row,0);

        MenuDAO dao = new MenuDAO();

        if(dao.deleteItem(id)){

            JOptionPane.showMessageDialog(this,"Item Deleted");

            refresh();
        }
    }

    private void refresh(){

        dispose();
        new AdminMenuFrame();
    }
}
