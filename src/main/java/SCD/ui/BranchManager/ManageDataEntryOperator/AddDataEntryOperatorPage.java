package SCD.ui.BranchManager.ManageDataEntryOperator;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.BranchManager.BranchSidebar;

import javax.swing.*;
import java.awt.*;

public class AddDataEntryOperatorPage extends JFrame {

    private BranchSidebar sidebar;
    private NavBar navBar;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField branchCodeField;
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

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Operator Name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel branchCodeLabel = new JLabel("Branch Code (BR-XXXX):");
        branchCodeField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(branchCodeLabel);
        formPanel.add(branchCodeField);

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

    public JTextField getBranchCodeField() {
        return branchCodeField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void clearFields() {
        nameField.setText("");
        emailField.setText("");
        branchCodeField.setText("");
    }
}
