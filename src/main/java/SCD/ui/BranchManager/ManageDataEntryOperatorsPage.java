package SCD.ui.BranchManager;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManageDataEntryOperatorsPage extends JFrame {
    private BranchSidebar sidebar;
    private DefaultTableModel tableModel;
    private NavBar navBar;

    public ManageDataEntryOperatorsPage() {
        setTitle("Manage Data Entry Operators");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Manage Data Entry Operators");

        String[] columnNames = {"Operator ID", "Name", "Contact", "Branch Code"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable operatorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(operatorTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("Operator ID (DH-1234):");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        JTextField contactField = new JTextField();
        JLabel branchCodeLabel = new JLabel("Branch Code:");
        JTextField branchCodeField = new JTextField();

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton addButton = ButtonFactory.createStyledButton("Add");
        JButton updateButton = ButtonFactory.createStyledButton("Update");
        JButton deleteButton = ButtonFactory.createStyledButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        contentPanel.add(formPanel, BorderLayout.SOUTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();
            String branchCode = branchCodeField.getText().trim();

            if (validateInputs(id, name, contact, branchCode)) {
                tableModel.addRow(new Object[]{id, name, contact, branchCode});
                clearFields(idField, nameField, contactField, branchCodeField);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = operatorTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an operator to update!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String contact = contactField.getText().trim();
                String branchCode = branchCodeField.getText().trim();

                if (validateInputs(id, name, contact, branchCode)) {
                    tableModel.setValueAt(id, selectedRow, 0);
                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(contact, selectedRow, 2);
                    tableModel.setValueAt(branchCode, selectedRow, 3);
                    clearFields(idField, nameField, contactField, branchCodeField);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = operatorTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an operator to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.removeRow(selectedRow);
                clearFields(idField, nameField, contactField, branchCodeField);
            }
        });
    }

    private boolean validateInputs(String id, String name, String contact, String branchCode) {
        if (!id.matches("DH-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Operator ID must follow the format 'DH-1234'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (name.isEmpty() || !name.matches("[A-Za-z\\s]+")) {
            JOptionPane.showMessageDialog(this, "Name must contain only letters", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!contact.matches("03\\d{2}-\\d{7}")) {
            JOptionPane.showMessageDialog(this, "Contact must follow the format '0321-1234567'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!branchCode.matches("BH-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Branch Code must follow the format 'BH-1234'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField contactField, JTextField branchCodeField) {
        idField.setText("");
        nameField.setText("");
        contactField.setText("");
        branchCodeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManageDataEntryOperatorsPage frame = new ManageDataEntryOperatorsPage();
            frame.setVisible(true);
        });
    }
}
