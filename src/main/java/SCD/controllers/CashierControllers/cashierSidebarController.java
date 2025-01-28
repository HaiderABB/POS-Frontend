package SCD.controllers.CashierControllers;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import SCD.controllers.BranchManagerControllers.ViewSalesController;
import SCD.controllers.CommonControllers.MainMenuController;
import SCD.ui.Cashier.cashierSidebar;

public class cashierSidebarController {

    private final cashierSidebar view;
    private final JFrame parentFrame;

    public cashierSidebarController(JFrame parentFrame) {
        this.view = new cashierSidebar();
        this.parentFrame = parentFrame;
        initController();
    }

    private void initController() {
        view.getDashboardButton().addActionListener(e -> openDashboard());
        view.getSalesPageButton().addActionListener(e -> openSalesPage());
        view.getSettingsButton().addActionListener(e -> openSettings());
        view.getLogoutButton().addActionListener(e -> performLogout());
    }

    public void openDashboard() {
        SwingUtilities.invokeLater(() -> {
            parentFrame.dispose(); // scd- proj initClose the current frame
            new CashierDashboardController();
        });
    }

    private void openSalesPage() {
        SwingUtilities.invokeLater(() -> {
            parentFrame.dispose(); // scd- proj initClose the current frame
            new SalesPageController();
        });
    }

    private void openViewSales() {
        SwingUtilities.invokeLater(() -> {
            parentFrame.dispose(); // scd- proj initClose the current frame
            new ViewSalesController();
        });
    }

    private void openSettings() {
        SwingUtilities.invokeLater(() -> {
            parentFrame.dispose(); // scd- proj initClose the current frame
            new SettingsPageController();
        });
    }

    private void performLogout() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            for (Window window : Window.getWindows()) {
                if (window.isShowing()) {
                    window.dispose(); // scd- proj initClose the window
                }
            }
            navigateToPage(MainMenuController::new);
        } else {
            return;
        }
    }

    private void navigateToPage(Runnable pageController) {
        SwingUtilities.invokeLater(() -> {
            parentFrame.dispose();
            pageController.run();
        });
    }

    public JPanel getView() {
        return view;
    }
}
