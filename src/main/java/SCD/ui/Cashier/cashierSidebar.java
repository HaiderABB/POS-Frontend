package SCD.ui.Cashier;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import SCD.ui.Common.Props;
import java.awt.*;

public class cashierSidebar extends JPanel implements Props {

    private JButton dashboardButton;
    private JButton salesPageButton;
    private JButton viewSalesButton;
    private JButton settingsButton;
    private JButton logoutButton;

    public cashierSidebar() {
        setPreferredSize(new Dimension(200, 700));
        setBackground(Props.bg);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        dashboardButton = createButton("Dashboard");
        salesPageButton = createButton("Sales Page");

        settingsButton = createButton("Settings");
        logoutButton = createButton("Log Out");

        add(dashboardButton);
        add(salesPageButton);
        add(settingsButton);
        add(logoutButton);
    }

    private JButton createButton(String label) {
        JButton button = ButtonFactory.createStyledButton(label);
        return button;
    }

    public JButton getDashboardButton() {
        return dashboardButton;
    }

    public JButton getSalesPageButton() {
        return salesPageButton;
    }

    public JButton getViewSalesButton() {
        return viewSalesButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
