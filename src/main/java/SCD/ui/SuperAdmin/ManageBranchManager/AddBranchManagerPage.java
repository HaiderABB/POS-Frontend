package SCD.ui.SuperAdmin.ManageBranchManager;

import javax.swing.*;
import java.awt.*;
import SCD.ui.Common.Props;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class AddBranchManagerPage extends JFrame implements Props {
    private Sidebar sidebar;
    private NavBar navBar;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField branchCodeField;
    private JTextField phoneField; // scd- proj initAdded for phone number
    private JButton addButton;

    public AddBranchManagerPage() {
        setTitle("Add Branch Manager");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Add Branch Manager");

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // scd- proj initAdjusted rows for new field
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        emailField = new JTextField();
        branchCodeField = new JTextField();
        phoneField = new JTextField(); // scd- proj initPhone number field
        addButton = ButtonFactory.createStyledButton("Add Branch Manager");

        formPanel.add(new JLabel("Branch Manager Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Branch Code (BR-XXXX):"));
        formPanel.add(branchCodeField);
        formPanel.add(new JLabel("Phone Number (03XXXXXXXXX):")); // scd- proj initLabel for phone number
        formPanel.add(phoneField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getBranchCodeField() {
        return branchCodeField;
    }

    public JTextField getPhoneField() { // scd- proj initGetter for phone field
        return phoneField;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
