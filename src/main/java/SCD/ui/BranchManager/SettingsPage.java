package SCD.ui.BranchManager;




import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPage extends JFrame {
    private Sidebar sidebar;
    private NavBar navBar;

    public SettingsPage() {
        setTitle("Settings");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);


        navBar.setTitle("Settings");



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
                        JOptionPane.showMessageDialog(SCD.ui.BranchManager.SettingsPage.this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SCD.ui.BranchManager.SettingsPage.this, "Password fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
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
            SCD.ui.SuperAdmin.SystemSettingsPage frame = new SCD.ui.SuperAdmin.SystemSettingsPage();
            frame.setVisible(true);
        });
    }
}
