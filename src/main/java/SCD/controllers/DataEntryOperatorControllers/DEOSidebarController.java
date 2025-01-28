package SCD.controllers.DataEntryOperatorControllers;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import SCD.controllers.CommonControllers.MainMenuController;
import SCD.controllers.DataEntryOperatorControllers.ProductController.*;
import SCD.controllers.DataEntryOperatorControllers.VendorController.AddNewVendorController;
import SCD.controllers.DataEntryOperatorControllers.VendorController.RemoveVendorController;
import SCD.controllers.DataEntryOperatorControllers.VendorController.UpdateVendorController;
import SCD.controllers.DataEntryOperatorControllers.VendorController.ViewVendorsController;
import SCD.ui.DataEntryOperator.DEOSidebar;

public class DEOSidebarController {

    private final DEOSidebar view;
    private final JFrame parentFrame;

    public DEOSidebarController(JFrame parentFrame) {
        this.view = new DEOSidebar();
        this.parentFrame = parentFrame;
        initController();
    }

    private void initController() {
        view.getDashboardButton().addActionListener(e -> openDashboard());
        view.getAddVendorButton().addActionListener(e -> openAddVendor());
        view.getUpdateVendorButton().addActionListener(e -> openUpdateVendor());
        view.getRemoveVendorButton().addActionListener(e -> openRemoveVendor());
        view.getViewVendorsButton().addActionListener(e -> openViewVendors());
        view.getAddProductButton().addActionListener(e -> openAddProduct());
        view.getAddProductButton().addActionListener(e -> openAddProduct());
        view.getUpdateProductButton().addActionListener(e -> openUpdateProduct());
        view.getRemoveProductButton().addActionListener(e -> openRemoveProduct());
        view.getViewProductsButton().addActionListener(e -> openViewProducts());
        view.getViewProductsByVendorButton().addActionListener(e -> openViewByVendorProducts());
        view.getLogoutButton().addActionListener(e -> performLogout());
    }

    private void openViewByVendorProducts() {
        navigateToPage(ViewProductsByVendorController::new);
    }

    private void openDashboard() {
        navigateToPage(DataEntryOperatorController::new);
    }

    private void openViewVendors() {
        navigateToPage(ViewVendorsController::new);
    }

    private void openAddVendor() {
        navigateToPage(AddNewVendorController::new);
    }

    private void openUpdateVendor() {
        navigateToPage(UpdateVendorController::new);
    }

    private void openRemoveVendor() {
        navigateToPage(RemoveVendorController::new);
    }

    private void openViewProducts() {
        navigateToPage(ViewProductsController::new);
    }

    private void openAddProduct() {
        navigateToPage(AddProductController::new);
    }

    private void openUpdateProduct() {
        navigateToPage(UpdateProductController::new);
    }

    private void openRemoveProduct() {
        navigateToPage(RemoveProductController::new);
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
