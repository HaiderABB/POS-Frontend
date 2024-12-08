package SCD.ui.SuperAdmin.ManageBranches;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import SCD.model.models.Employee;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class AddBranchPage extends JFrame {
    private JTextField nameField;
    private JTextField cityField;
    private JTextField phoneField;
    private JTextField addressField;
    private JButton addButton;
    Employee employee;

    public AddBranchPage(Employee employee) {
        this.employee = employee;
        setTitle("Add Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Sidebar sidebar = new Sidebar(employee);
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        NavBar navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Add Branch");

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Branch Name:");
        nameField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone (0321-1234567):");
        phoneField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(cityLabel);
        formPanel.add(cityField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = ButtonFactory.createStyledButton("Add Branch");
        buttonPanel.add(addButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    // Getters for the fields and button to connect with the controller
    public String getNameField() {
        return nameField.getText().trim();
    }

    public String getCityField() {
        return cityField.getText().trim();
    }

    public String getPhoneField() {
        return phoneField.getText().trim();
    }

    public String getAddressField() {
        return addressField.getText().trim();
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void clearFields() {
        nameField.setText("");
        cityField.setText("");
        phoneField.setText("");
        addressField.setText("");
    }
}
