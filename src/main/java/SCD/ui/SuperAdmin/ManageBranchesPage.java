package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ManageBranchesPage extends JFrame {
    private Sidebar sidebar;
    private DefaultTableModel tableModel;
    private NavBar navBar;
    private boolean isSidebarVisible = true;

    public ManageBranchesPage() {
        setTitle("Manage Branches");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);



        JPanel contentPanel = new JPanel(new BorderLayout());


        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);


        navBar.setTitle("Manage Branches");


        String[] columnNames = {"Branch ID", "Branch Name", "Branch Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable branchTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(branchTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);


        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel idLabel = new JLabel("Branch ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Branch Name:");
        JTextField nameField = new JTextField();
        JLabel addressLabel = new JLabel("Branch Address:");
        JTextField addressField = new JTextField();

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);

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

        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.addRow(new Object[]{id, name, address});
                clearFields(idField, nameField, addressField);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = branchTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a branch to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String address = addressField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(address, selectedRow, 2);
                    clearFields(idField, nameField, addressField);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = branchTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a branch to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(idField, nameField, addressField);
            }
        });
    }
    private void toggleSidebar(ActionEvent e) {
        isSidebarVisible = !isSidebarVisible;
        sidebar.setVisible(isSidebarVisible);
        revalidate();
        repaint();
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField addressField) {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
    }
}