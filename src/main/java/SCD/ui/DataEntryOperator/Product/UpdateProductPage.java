package SCD.ui.DataEntryOperator.Product;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

import javax.swing.*;
import java.awt.*;

public class UpdateProductPage extends JFrame implements Props {

    private DEOSidebarController sidebar;
    private NavBar navBar;
    private JTextField productCodeField;
    private JComboBox<String> fieldComboBox;
    private JTextField newValueField;
    private JButton updateButton;
    private JButton validateButton;

    public UpdateProductPage() {
        setTitle("Update Product");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new DEOSidebarController(this);
        add(sidebar.getView(), BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        navBar.setTitle("Update Product");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel productCodeLabel = new JLabel("Product Code (PM-XXXX):");
        productCodeField = new JTextField();
        formPanel.add(productCodeLabel);
        formPanel.add(productCodeField);

        JLabel fieldLabel = new JLabel("Select Field to Update:");
        String[] fields = { "Name", "Category", "Stock", "Original Price", "Price per Unit", "Price per Carton" };
        fieldComboBox = new JComboBox<>(fields);
        formPanel.add(fieldLabel);
        formPanel.add(fieldComboBox);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueField = new JTextField();
        formPanel.add(newValueLabel);
        formPanel.add(newValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        validateButton = ButtonFactory.createStyledButton("Validate Field");
        updateButton = ButtonFactory.createStyledButton("Update Product");
        buttonPanel.add(validateButton);
        buttonPanel.add(updateButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public JTextField getProductCodeField() {
        return productCodeField;
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
