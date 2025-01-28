package SCD.ui.DataEntryOperator.Product;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Props;
import SCD.ui.DataEntryOperator.DEOSidebar;

import javax.swing.*;
import java.awt.*;

public class RemoveProductPage extends JFrame implements Props {

    private JTextField productIdField;
    private JButton removeButton;

    public RemoveProductPage() {
        setTitle("Remove Product");
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
        JLabel titleLabel = new JLabel("Remove Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // scd- proj initForm Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel productIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel productIdLabel = new JLabel("Product ID (PM-XXXX): ");
        productIdField = new JTextField();
        productIdField.setPreferredSize(new Dimension(200, 25));
        productIdPanel.add(productIdLabel);
        productIdPanel.add(productIdField);

        formPanel.add(productIdPanel);

        // scd- proj initButton Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        removeButton = ButtonFactory.createStyledButton("Remove Product");
        buttonPanel.add(removeButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getProductIdField() {
        return productIdField;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}
