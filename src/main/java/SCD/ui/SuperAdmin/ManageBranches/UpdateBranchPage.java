package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;

public class UpdateBranchPage extends JFrame {

    private JTextField branchCodeField;
    private JComboBox<String> fieldComboBox;
    private JTextField newValueField;
    private JButton validateButton;
    private JButton updateButton;

    public UpdateBranchPage() {
        setTitle("Update Branch");
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
        navBar.setTitle("Update Branch");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX):");
        branchCodeField = new JTextField();
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = {"Name", "City", "Phone", "Address"};
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Branch");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        // Add Panels
        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public String getBranchCode() {
        return branchCodeField.getText().trim();
    }

    public String getSelectedField() {
        return (String) fieldComboBox.getSelectedItem();
    }

    public String getNewValue() {
        return newValueField.getText().trim();
    }

    public JButton getValidateButton() {
        return validateButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void clearFields() {
        branchCodeField.setText("");
        newValueField.setText("");
    }
}
