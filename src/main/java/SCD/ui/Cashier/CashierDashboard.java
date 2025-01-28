package SCD.ui.Cashier;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import SCD.controllers.CashierControllers.cashierSidebarController;
import SCD.ui.Common.Props;

public class CashierDashboard extends JFrame implements Props {

    private JPanel contentPanel;

    public CashierDashboard() {
        setTitle("Cashier Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        cashierSidebarController sidebarController = new cashierSidebarController(this);
        JPanel sidebar = sidebarController.getView();
        add(sidebar, BorderLayout.WEST);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JLabel instructionsLabel = new JLabel(
                "<html><h1>Welcome to the Cashier Dashboard</h1><p>Use the sidebar to navigate to different pages.</p></html>");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(instructionsLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public void showDashboard() {
        setVisible(true);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
