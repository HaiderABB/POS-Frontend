package SCD.ui.BranchManager.ManageDataEntryOperator;

import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;

public class UpdateDataEntryPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;
    private JTextField dataEntryCodeField;
    private JComboBox<String> fieldComboBox;
    private JTextField newValueField;
    private JButton validateButton;
    private JButton updateButton;

    public UpdateDataEntryPage() {
        setTitle("Update Data Entry Personnel");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("Update Data Entry Personnel");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel dataEntryCodeLabel = new JLabel("Data Entry Code (DM-XXXX):");
        dataEntryCodeField = new JTextField();
        formPanel.add(dataEntryCodeLabel);
        formPanel.add(dataEntryCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = {"Name", "Email", "Branch Code"};
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Data Entry");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getDataEntryCodeField() {
        return dataEntryCodeField;
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

    public void clearFields() {
        dataEntryCodeField.setText("");
        newValueField.setText("");
    }


}
