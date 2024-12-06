package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class AddBranchPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;

    public AddBranchPage() {
        setTitle("Add Branch");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Add Branch");

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Branch Name:");
        JTextField nameField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        JTextField cityField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone (0321-1234567):");
        JTextField phoneField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(cityLabel);
        formPanel.add(cityField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = ButtonFactory.createStyledButton("Add Branch");
        buttonPanel.add(addButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String city = cityField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();

            if (!validateInputs(name, city, phone, address)) {
                return;
            }

            saveBranch(name, city, phone, address);
            clearFields(nameField, cityField, phoneField, addressField);
        });
    }

    private boolean validateInputs(String name, String city, String phone, String address) {
        if (name.isEmpty() || city.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("[A-Za-z\\s]+", city)) {
            JOptionPane.showMessageDialog(this, "City must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!Pattern.matches("03\\d{2}-\\d{7}", phone)) {
            JOptionPane.showMessageDialog(this, "Phone must follow the format '0321-1234567'.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void saveBranch(String name, String city, String phone, String address) {
        // Simulate saving to a database or performing some action
        String message = "Branch successfully added:\n"
                + "Name: " + name + "\n"
                + "City: " + city + "\n"
                + "Phone: " + phone + "\n"
                + "Address: " + address;
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields(JTextField nameField, JTextField cityField, JTextField phoneField, JTextField addressField) {
        nameField.setText("");
        cityField.setText("");
        phoneField.setText("");
        addressField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddBranchPage frame = new AddBranchPage();
            frame.setVisible(true);
        });
    }
}
