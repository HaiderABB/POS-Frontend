package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.AddCashierPage;
import SCD.ui.BranchManager.ManageCashier.ViewCashiersPage;

import javax.swing.*;

public class ViewCashiersController {

    private  ViewCashiersPage view;

    public ViewCashiersController() {
        SwingUtilities.invokeLater(() -> {
            ViewCashiersPage page = new ViewCashiersPage();
            page.setVisible(true);
        });

    }
    public ViewCashiersController(ViewCashiersPage view) {
        this.view = view;

    }



    public static void main(String[] args) {
        ViewCashiersPage page = new ViewCashiersPage();
        new ViewCashiersController(page);
        page.setVisible(true);
    }
}
