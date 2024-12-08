package SCD.ui.SuperAdmin.ManageBranchManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SCD.model.models.Employee;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class UpdateBranchManagerPage extends JFrame {
    private JTextField managerCodeField;
    private JComboBox<String> fieldComboBox;
    private JTextField newValueField;
    private JButton validateButton;
    private JButton updateButton;
    Employee employee;

    public UpdateBranchManagerPage(Employee employee) {
        this.employee = employee;
        setTitle("Update Branch Manager");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Sidebar sidebar = new Sidebar(employee);
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        NavBar navBar = new NavBar();
        navBar.setTitle("Update Branch Manager");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel managerCodeLabel = new JLabel("Branch Manager Code (BM-XXXX):");
        managerCodeField = new JTextField();
        formPanel.add(managerCodeLabel);
        formPanel.add(managerCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = { "Name", "Email", "Branch Code" };
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Manager");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public JTextField getManagerCodeField() {
        return managerCodeField;
    }

    public JComboBox<String> getFieldComboBox() {
        return fieldComboBox;
    }

    public JTextField getNewValueField() {
        return newValueField;
    }

    public JButton getValidateButton() {
        return validateButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
}
