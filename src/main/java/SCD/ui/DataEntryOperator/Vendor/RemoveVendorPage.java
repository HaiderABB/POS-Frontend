package SCD.ui.DataEntryOperator.Vendor;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Props;

import javax.swing.*;
import java.awt.*;

public class RemoveVendorPage extends JFrame implements Props {

    private JTextField vendorIdField;
    private JButton removeButton;

    public RemoveVendorPage() {
        setTitle("Remove Vendor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initSidebar
        DEOSidebarController sidebar = new DEOSidebarController(this);
        add(sidebar.getView(), BorderLayout.WEST);

        // scd- proj initContent Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // scd- proj initHeader
        JLabel titleLabel = new JLabel("Remove Vendor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // scd- proj initForm Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel vendorIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel vendorIdLabel = new JLabel("Vendor ID (VM-XXXX): ");
        vendorIdField = new JTextField();
        vendorIdField.setPreferredSize(new Dimension(200, 25));
        vendorIdPanel.add(vendorIdLabel);
        vendorIdPanel.add(vendorIdField);

        formPanel.add(vendorIdPanel);

        // scd- proj initButton Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        removeButton = ButtonFactory.createStyledButton("Remove Vendor");
        buttonPanel.add(removeButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getVendorIdField() {
        return vendorIdField;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

}
