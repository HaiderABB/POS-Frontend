package SCD.ui.Cashier;

import SCD.controllers.CashierControllers.cashierSidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;
import SCD.ui.Common.Props;

import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JFrame implements Props {
    private cashierSidebar sidebar;
    private NavBar navBar;
    private JButton changePasswordButton;

    public SettingsPage() {
        setTitle("Settings");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        cashierSidebarController sidebarController = new cashierSidebarController(this);
        JPanel sidebar = sidebarController.getView();
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
}
