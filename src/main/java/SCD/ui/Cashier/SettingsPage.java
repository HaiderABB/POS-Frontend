package SCD.ui.Cashier;
import SCD.ui.Common.ButtonFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsPage extends JFrame {

    private cashierSidebar sidebar;

    public SettingsPage() {
        setTitle("Settings");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add sidebar
        sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        // Main content area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel currentPasswordLabel = new JLabel("Current Password:");
        JPasswordField currentPasswordField = new JPasswordField();
        JLabel newPasswordLabel = new JLabel("New Password:");
        JPasswordField newPasswordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm New Password:");
        JPasswordField confirmPasswordField = new JPasswordField();

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setMaximumSize(new Dimension(180, 40));
        changePasswordButton.setFont(new Font("", Font.BOLD, 15));
        changePasswordButton.setBackground(new Color(255, 102, 102));
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.setBorderPainted(false);

        // Mouse hover effects
        changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changePasswordButton.setBackground(new Color(200, 50, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changePasswordButton.setBackground(new Color(255, 102, 102));
            }
        });
        changePasswordButton.addActionListener(e -> {
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "New passwords do not match!");
                return;
            }

            // Implement password change logic here
            // For example, validate current password and update to new password in the database

            JOptionPane.showMessageDialog(this, "Password changed successfully!");
        });

        mainPanel.add(currentPasswordLabel);
        mainPanel.add(currentPasswordField);
        mainPanel.add(newPasswordLabel);
        mainPanel.add(newPasswordField);
        mainPanel.add(confirmPasswordLabel);
        mainPanel.add(confirmPasswordField);
        mainPanel.add(new JLabel()); // Empty cell
        mainPanel.add(changePasswordButton);

        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SettingsPage().setVisible(true);
        });
    }
}