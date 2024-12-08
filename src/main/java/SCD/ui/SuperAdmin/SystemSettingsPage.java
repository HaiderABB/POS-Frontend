package SCD.ui.SuperAdmin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import SCD.model.models.Employee;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

public class SystemSettingsPage extends JFrame {
    private Sidebar sidebar;
    private NavBar navBar;
    private JButton changePasswordButton;
    Employee employee;

    public SystemSettingsPage(Employee employee) {
        this.employee = employee;
        setTitle("System Settings");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar(employee);
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("System Settings");

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        JLabel changePasswordLabel = new JLabel("Change Password: ");
        changePasswordButton = ButtonFactory.createStyledButton("Change");
        changePasswordButton.setPreferredSize(new Dimension(150, 30));

        formPanel.add(changePasswordLabel);
        formPanel.add(changePasswordButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public void showPasswordChangeDialog(JPanel passwordPanel) {
        JOptionPane.showConfirmDialog(
                this,
                passwordPanel,
                "Change Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
