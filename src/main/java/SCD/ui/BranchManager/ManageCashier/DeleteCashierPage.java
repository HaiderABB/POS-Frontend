package SCD.ui.BranchManager.ManageCashier;

import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;

public class DeleteCashierPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;

    public DeleteCashierPage() {
        setTitle("Delete Cashier");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("Delete Cashier");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel cashierCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel cashierCodeLabel = new JLabel("Cashier ID (CM-XXXX): ");
        JTextField cashierCodeField = new JTextField();
        cashierCodeField.setPreferredSize(new Dimension(200, 25));
        cashierCodePanel.add(cashierCodeLabel);
        cashierCodePanel.add(cashierCodeField);

        formPanel.add(cashierCodePanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton deleteButton = ButtonFactory.createStyledButton("Delete Cashier");
        buttonPanel.add(deleteButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        deleteButton.addActionListener(e -> {
            String cashierCode = cashierCodeField.getText().trim();

            if (!validateCashierCode(cashierCode)) {
                return;
            }

            deleteCashier(cashierCode);
            cashierCodeField.setText("");
        });
    }

    private boolean validateCashierCode(String cashierCode) {
        if (cashierCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Cashier ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cashierCode.matches("CM-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Cashier ID must follow the format 'CM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteCashier(String cashierCode) {
        JOptionPane.showMessageDialog(this, "Cashier with ID " + cashierCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteCashierPage frame = new DeleteCashierPage();
            frame.setVisible(true);
        });
    }
}
