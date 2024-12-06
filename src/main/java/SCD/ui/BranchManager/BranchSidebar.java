package SCD.ui.BranchManager;

import SCD.ui.BranchManager.ManageCashier.AddCashierPage;
import SCD.ui.BranchManager.ManageCashier.DeleteCashierPage;
import SCD.ui.BranchManager.ManageCashier.ViewCashiersPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.AddDataEntryOperatorPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.DeleteDataEntryOperatorPage;
import SCD.ui.BranchManager.ManageDataEntryOperator.ViewDataEntryOperatorsPage;
import SCD.ui.Common.ButtonFactory;

import javax.swing.*;
import java.awt.*;

public class BranchSidebar extends JPanel {

    public BranchSidebar() {
        setPreferredSize(new Dimension(240, 700));
        setBackground(new Color(255, 102, 102));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        add(createButton("Dashboard", this::openDashboard));

        add(createButton("Add Cashier", this::openAddCashier));
        add(createButton("Delete Cashier", this::openDeleteCashier));
        add(createButton("View Cashiers", this::openViewCashiers));

        add(createButton("Add Data Entry Operator", this::openAddDataEntryOperator));
        add(createButton("Delete Data Entry Operator", this::openDeleteDataEntryOperator));
        add(createButton("View Data Entry Operators", this::openViewDataEntryOperators));

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


    private void openAddCashier() {
        navigateToPage(new AddCashierPage());
    }

    private void openDeleteCashier() {
        navigateToPage(new DeleteCashierPage());
    }

    private void openViewCashiers() {
        navigateToPage(new ViewCashiersPage());
    }


    private void openAddDataEntryOperator() {
        navigateToPage(new AddDataEntryOperatorPage());
    }

    private void openDeleteDataEntryOperator() {
        navigateToPage(new DeleteDataEntryOperatorPage());
    }

    private void openViewDataEntryOperators() {
        navigateToPage(new ViewDataEntryOperatorsPage());
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
