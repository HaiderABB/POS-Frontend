package SCD.ui.BranchManager;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryManagementPage extends JFrame {
    private DefaultTableModel tableModel;
    private NavBar navBar;

    public InventoryManagementPage() {
        setTitle("Inventory Management");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        BranchSidebar sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

      JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);

       navBar.setTitle("Inventory Management");

        String[] columnNames = {"Item ID", "Item Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable inventoryTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("Item ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton addButton = ButtonFactory.createStyledButton("Add");
        JButton updateButton = ButtonFactory.createStyledButton("Update");
        JButton deleteButton = ButtonFactory.createStyledButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        formPanel.add(buttonPanel);
        contentPanel.add(formPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);

       addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String quantity = quantityField.getText().trim();
            String price = priceField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.addRow(new Object[]{id, name, quantity, price});
                clearFields(idField, nameField, quantityField, priceField);
            }
        });


        updateButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String quantity = quantityField.getText().trim();
                String price = priceField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(quantity, selectedRow, 2);
                    tableModel.setValueAt(price, selectedRow, 3);
                    clearFields(idField, nameField, quantityField, priceField);
                }
            }
        });


        deleteButton.addActionListener(e -> {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an item to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(idField, nameField, quantityField, priceField);
            }
        });
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField quantityField, JTextField priceField) {
        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }
}
