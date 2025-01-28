package SCD.ui.SuperAdmin.ManageBranches;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import SCD.ui.Common.Props;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class UpdateBranchPage extends JFrame implements Props {

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

        // scd- proj initSidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // scd- proj initNavBar
        NavBar navBar = new NavBar();
        navBar.setTitle("Update Branch");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // scd- proj initForm Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX):");
        branchCodeField = new JTextField();
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = { "Name", "City", "Phone", "Address" };
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        // scd- proj initButton Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Branch");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        // scd- proj initAdd Panels
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
