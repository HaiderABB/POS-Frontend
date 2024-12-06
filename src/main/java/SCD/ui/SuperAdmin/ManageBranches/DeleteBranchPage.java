package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;

public class DeleteBranchPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;

    public DeleteBranchPage() {
        setTitle("Delete Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // NavBar
        navBar = new NavBar();
        navBar.setTitle("Delete Branch");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel branchCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX): ");
        JTextField branchCodeField = new JTextField();
        branchCodeField.setPreferredSize(new Dimension(200, 25));
        branchCodePanel.add(branchCodeLabel);
        branchCodePanel.add(branchCodeField);

        formPanel.add(branchCodePanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton deleteButton = ButtonFactory.createStyledButton("Delete Branch");
        buttonPanel.add(deleteButton);

        // Add Panels to Content
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        // Button Action
        deleteButton.addActionListener(e -> {
            String branchCode = branchCodeField.getText().trim();

            if (!validateBranchCode(branchCode)) {
                return;
            }

            deleteBranch(branchCode);
            branchCodeField.setText("");
        });
    }

    private boolean validateBranchCode(String branchCode) {
        if (branchCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Branch Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!branchCode.matches("BR-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Branch Code must follow the format 'BR-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteBranch(String branchCode) {
        JOptionPane.showMessageDialog(this, "Branch with Code " + branchCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchPage frame = new DeleteBranchPage();
            frame.setVisible(true);
        });
    }
}
