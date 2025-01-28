package SCD.ui.BranchManager.ManageCashier;

import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

import javax.swing.*;
import java.awt.*;

public class UpdateCashierPage extends JFrame implements Props {

    private BranchSidebar sidebar;
    private NavBar navBar;
    private JTextField cashierCodeField;
    private JComboBox<String> fieldComboBox;
    private JTextField newValueField;
    private JButton updateButton;
    private JButton validateButton;

    public UpdateCashierPage() {
        setTitle("Update Cashier");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Cashier");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel cashierCodeLabel = new JLabel("Cashier Code (CM-XXXX):");
        cashierCodeField = new JTextField();
        formPanel.add(cashierCodeLabel);
        formPanel.add(cashierCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = { "Name", "Email", "Branch Code", "Phone Number" }; // scd- proj initAdded "Phone Number"
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Cashier");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public JTextField getCashierCodeField() {
        return cashierCodeField;
    }

    public JComboBox<String> getFieldComboBox() {
        return fieldComboBox;
    }

    public JTextField getNewValueField() {
        return newValueField;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getValidateButton() {
        return validateButton;
    }
}
