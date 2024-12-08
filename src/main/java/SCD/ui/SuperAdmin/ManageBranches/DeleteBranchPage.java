package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;

public class DeleteBranchPage extends JFrame {

    private JTextField branchCodeField;
    private JButton deleteButton;

    public DeleteBranchPage() {
        setTitle("Delete Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // NavBar
        NavBar navBar = new NavBar();
        navBar.setTitle("Delete Branch");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel branchCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX): ");
        branchCodeField = new JTextField();
        branchCodeField.setPreferredSize(new Dimension(200, 25));
        branchCodePanel.add(branchCodeLabel);
        branchCodePanel.add(branchCodeField);

        formPanel.add(branchCodePanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        deleteButton = ButtonFactory.createStyledButton("Delete Branch");
        buttonPanel.add(deleteButton);

        // Add Panels to Content
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    // Getters for input field and button to connect with the controller
    public String getBranchCode() {
        return branchCodeField.getText().trim();
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void clearBranchCodeField() {
        branchCodeField.setText("");
    }
}
