package SCD.ui.Cashier;

import javax.swing.*;
import java.awt.*;

public class CashierDashboard extends JFrame {

    private cashierSidebar sidebar;

    public CashierDashboard() {
        setTitle("Cashier Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        sidebar = new cashierSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JLabel instructionsLabel = new JLabel("<html><h1>Welcome to the Cashier Dashboard</h1><p>Use the sidebar to navigate to different pages.</p></html>");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(instructionsLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CashierDashboard dashboard = new CashierDashboard();
            dashboard.setVisible(true);
        });
    }
}