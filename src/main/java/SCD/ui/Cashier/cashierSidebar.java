package SCD.ui.Cashier;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class cashierSidebar extends JPanel {

    public cashierSidebar() {
        setPreferredSize(new Dimension(200, 700));
        setBackground(new Color(255, 102, 102));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        add(createButton("Dashboard", this::openDashboard));
        add(createButton("Cashier Interface", this::openCashierInterface));
        add(createButton("Settings", this::openSettings));
        add(createButton("Log Out", this::performLogout));
    }

    private JButton createButton(String label, Runnable action) {
        JButton button = ButtonFactory.createStyledButton(label);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void openDashboard() {
        navigateToPage(new CashierDashboard());
    }

    private void openProcessPayment() {
//        navigateToPage(new ProcessPaymentPage());
    }

    private void openViewSales() {
        navigateToPage(new ViewSales());
    }

    private void openCashierInterface() {
        navigateToPage(new CashierInterface());
    }

    private void openRefund() {
//        navigateToPage(new RefundFrame());
    }

    private void openSettings() {
        navigateToPage(new SettingsPage());
    }

    private void performLogout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        System.exit(0);
    }

    private void navigateToPage(JFrame page) {
        SwingUtilities.invokeLater(() -> {
            Window topLevelWindow = SwingUtilities.getWindowAncestor(this);
            if (topLevelWindow instanceof JFrame) {
                ((JFrame) topLevelWindow).dispose();
            }
            page.setVisible(true);
        });
    }
}
