package SCD.ui.BranchManager;

import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class BranchSidebar extends JPanel {

    public BranchSidebar() {
        setPreferredSize(new Dimension(200, 700));
        setBackground(new Color(255, 102, 102));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        add(createButton("Dashboard", this::openDashboard));
        add(createButton("Cashiers", this::openCashier));
        add(createButton("Data Entry Operators", this::openManageDataEntryOperators));

        add(createButton("Settings", this::openSettings));
        add(createButton("Logout", this::performLogout));
    }

    private JButton createButton(String label, Runnable action) {
        JButton button = ButtonFactory.createStyledButton(label);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void openDashboard() {
        navigateToPage(new BranchManagerDashboard());
    }

    private void openCashier() {
        navigateToPage(new ManageCashiersPage());
    }

    private void openManageDataEntryOperators() {
        navigateToPage(new ManageDataEntryOperatorsPage());
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
