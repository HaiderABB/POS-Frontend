package SCD.ui.SuperAdmin.ManageBranchManager;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;

public class DeleteBranchManagerPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;

    public DeleteBranchManagerPage() {
        setTitle("Delete Branch Manager");
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
        navBar.setTitle("Delete Branch Manager");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel managerCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel managerCodeLabel = new JLabel("Branch Manager Code (BM-XXXX): ");
        JTextField managerCodeField = new JTextField();
        managerCodeField.setPreferredSize(new Dimension(200, 25));
        managerCodePanel.add(managerCodeLabel);
        managerCodePanel.add(managerCodeField);

        formPanel.add(managerCodePanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton deleteButton = ButtonFactory.createStyledButton("Delete Branch Manager");
        buttonPanel.add(deleteButton);

        // Add Panels to Content
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        // Button Action
        deleteButton.addActionListener(e -> {
            String managerCode = managerCodeField.getText().trim();

            if (!validateManagerCode(managerCode)) {
                return;
            }

            deleteBranchManager(managerCode);
            managerCodeField.setText("");
        });
    }

    private boolean validateManagerCode(String managerCode) {
        if (managerCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Branch Manager Code!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!managerCode.matches("BM-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Branch Manager Code must follow the format 'BM-XXXX'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void deleteBranchManager(String managerCode) {
        JOptionPane.showMessageDialog(this, "Branch Manager with Code " + managerCode + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteBranchManagerPage frame = new DeleteBranchManagerPage();
            frame.setVisible(true);
        });
    }
}
