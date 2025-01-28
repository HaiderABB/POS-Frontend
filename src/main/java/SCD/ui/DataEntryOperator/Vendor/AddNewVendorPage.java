package SCD.ui.DataEntryOperator.Vendor;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class AddNewVendorPage extends JFrame implements Props {

    private JTextField vendorNameField;
    private JTextField addressField;
    private JTextField phoneNumberField;
    private JButton addButton;

    public AddNewVendorPage() {
        setTitle("Add New Vendor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initSidebar
        DEOSidebarController sidebar = new DEOSidebarController(this);
        add(sidebar.getView(), BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // scd- proj initNavbar
        NavBar navBar = new NavBar();
        navBar.setTitle("Add New Vendor");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // scd- proj initForm Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel vendorNameLabel = new JLabel("Vendor Name:");
        vendorNameField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone Number (03XXXXXXXXX):");
        phoneNumberField = new JTextField();

        formPanel.add(vendorNameLabel);
        formPanel.add(vendorNameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneNumberField);

        // scd- proj initButton Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = ButtonFactory.createStyledButton("Add Vendor");
        JButton cancelButton = ButtonFactory.createStyledButton("Cancel");

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        cancelButton.addActionListener(e -> dispose());
    }

    public JTextField getVendorNameField() {
        return vendorNameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JButton getAddButton() {
        return addButton;
    }

}
