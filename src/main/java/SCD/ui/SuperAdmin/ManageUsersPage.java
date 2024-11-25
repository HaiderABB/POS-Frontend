package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Sidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUsersPage extends JFrame {
    private Sidebar sidebar;
    private DefaultTableModel tableModel;

    public ManageUsersPage() {
        setTitle("Manage Users");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] menuItems = {"Dashboard", "Manage Users", "Manage Branches", "View Reports", "System Settings", "Logout"};
        sidebar = new Sidebar(menuItems, "C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\companyLogo.png");
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Manage Users", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Name", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

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
            String email = emailField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.addRow(new Object[]{id, name, email});
                clearFields(idField, nameField, emailField);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a user to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(email, selectedRow, 2);
                    clearFields(idField, nameField, emailField);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a user to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(idField, nameField, emailField);
            }
        });
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField emailField) {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
    }
}
