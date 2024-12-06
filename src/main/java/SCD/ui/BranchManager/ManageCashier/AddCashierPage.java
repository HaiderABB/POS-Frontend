package SCD.ui.BranchManager.ManageCashier;

import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class AddCashierPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;

    public AddCashierPage() {
        setTitle("Add Cashier");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Add Cashier");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Cashier Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel branchCodeLabel = new JLabel("Branch Code (BH-XXXX):");
        JTextField branchCodeField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = ButtonFactory.createStyledButton("Add Cashier");
        buttonPanel.add(addButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String branchCode = branchCodeField.getText().trim();

            if (!validateInputs(name, email, branchCode)) {
                return;
            }

            saveCashier(name, email, branchCode);
            clearFields(nameField, emailField, branchCodeField);
        });
    }

    private boolean validateInputs(String name, String email, String branchCode) {
        if (name.isEmpty() || email.isEmpty() || branchCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("BH-\\d{4}", branchCode)) {
            JOptionPane.showMessageDialog(this, "Branch Code must follow the format 'BH-XXXX'!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void saveCashier(String name, String email, String branchCode) {
        String message = "Cashier successfully added:\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Branch Code: " + branchCode;
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields(JTextField nameField, JTextField emailField, JTextField branchCodeField) {
        nameField.setText("");
        emailField.setText("");
        branchCodeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddCashierPage frame = new AddCashierPage();
            frame.setVisible(true);
        });
    }
}
