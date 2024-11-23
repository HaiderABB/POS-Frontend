package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemSettingsPage extends JFrame {
    private Sidebar sidebar;

    public SystemSettingsPage() {
        setTitle("System Settings");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] menuItems = {"Dashboard", "Manage Users", "Manage Branches", "View Reports", "System Settings", "Logout"};
        sidebar = new Sidebar(menuItems, "C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\companyLogo.png");
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JLabel titleLabel = new JLabel("System Settings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        JLabel changePasswordLabel = new JLabel("Change Password: ");
        JButton changePasswordButton = ButtonFactory.createStyledButton("Change");

        changePasswordButton.setPreferredSize(new Dimension(150, 30));

        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel passwordPanel = new JPanel(new GridLayout(2, 2));
                JPasswordField oldPasswordField = new JPasswordField(15);
                JPasswordField newPasswordField = new JPasswordField(15);
                passwordPanel.add(new JLabel("Old Password:"));
                passwordPanel.add(oldPasswordField);
                passwordPanel.add(new JLabel("New Password:"));
                passwordPanel.add(newPasswordField);

                int option = JOptionPane.showConfirmDialog(null, passwordPanel, "Enter Old and New Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    char[] oldPassword = oldPasswordField.getPassword();
                    char[] newPassword = newPasswordField.getPassword();
                    if (oldPassword.length > 0 && newPassword.length > 0) {
                        JOptionPane.showMessageDialog(SystemSettingsPage.this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SystemSettingsPage.this, "Password fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        formPanel.add(changePasswordLabel);
        formPanel.add(changePasswordButton);

        contentPanel.add(formPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SystemSettingsPage frame = new SystemSettingsPage();
            frame.setVisible(true);
        });
    }
}
