package SCD.ui.DataEntryOperator.Vendor;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import SCD.ui.Common.Props;

public class UpdateVendorPage extends JFrame implements Props {

    private DEOSidebarController sidebar;
    private NavBar navBar;
    private JTextField vendorCodeField;
    private JComboBox<String> fieldComboBox;
    private JTextField newValueField;
    private JButton updateButton;
    private JButton validateButton;

    public UpdateVendorPage() {
        setTitle("Update Vendor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new DEOSidebarController(this);
        add(sidebar.getView(), BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Vendor");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel vendorCodeLabel = new JLabel("Vendor Code (VM-XXXX):");
        vendorCodeField = new JTextField();
        formPanel.add(vendorCodeLabel);
        formPanel.add(vendorCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = { "Name", "Address", "Phone" }; // scd- proj initRemoved "Email"
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Vendor");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public JTextField getVendorCodeField() {
        return vendorCodeField;
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
