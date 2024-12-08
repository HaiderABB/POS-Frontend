package SCD.controllers.BranchManagerControllers.CashierController;

import SCD.ui.BranchManager.ManageCashier.ViewCashiersPage;

public class ViewCashiersController {

    private final ViewCashiersPage view;

    public ViewCashiersController(ViewCashiersPage view) {
        this.view = view;

    }



    public static void main(String[] args) {
        ViewCashiersPage page = new ViewCashiersPage();
        new ViewCashiersController(page);
        page.setVisible(true);
    }
}
