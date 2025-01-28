package SCD.ui.BranchManager.ManageDataEntryOperator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import SCD.ui.Common.Props;

import SCD.ui.BranchManager.BranchSidebar;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

public class AddDataEntryOperatorPage extends JFrame implements Props {

    private BranchSidebar sidebar;
    private NavBar navBar;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField; // scd- proj initAdded phone number field
    private JButton addButton;

    public AddDataEntryOperatorPage() {
        setTitle("Add Data Entry Operator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new BranchSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("Add Data Entry Operator");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // scd- proj initAdjusted grid layout for new field
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Operator Name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone Number (03XXXXXXXXX):"); // scd- proj initAdded label for phone number
        phoneField = new JTextField(); // scd- proj initAdded text field for phone number

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = ButtonFactory.createStyledButton("Add Operator");
        buttonPanel.add(addButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneField() { // scd- proj initGetter for phone number field
        return phoneField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText(""); // scd- proj initClear phone number field
    }
}
