package SCD.ui.BranchManager;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageStaffPage extends JFrame {
    private DefaultTableModel tableModel;
    private NavBar navBar;

    public ManageStaffPage() {
        setTitle("Manage Staff");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

       BranchSidebar sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);

        navBar.setTitle("Manage Staff");

        String[] columnNames = {"Staff ID", "Name", "Role", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable staffTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(staffTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("Staff ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        JTextField contactField = new JTextField();

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(roleLabel);
        formPanel.add(roleField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);

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
            String role = roleField.getText().trim();
            String contact = contactField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || role.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.addRow(new Object[]{id, name, role, contact});
                clearFields(idField, nameField, roleField, contactField);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a staff member to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String role = roleField.getText().trim();
                String contact = contactField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || role.isEmpty() || contact.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(role, selectedRow, 2);
                    tableModel.setValueAt(contact, selectedRow, 3);
                    clearFields(idField, nameField, roleField, contactField);
                }
            }
        });

         deleteButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a staff member to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(idField, nameField, roleField, contactField);
            }
        });
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField roleField, JTextField contactField) {
        idField.setText("");
        nameField.setText("");
        roleField.setText("");
        contactField.setText("");
    }
}
