package SCD.controllers.CashierControllers;

import SCD.ui.Cashier.CashierDashboard;

public class CashierDashboardController {

    private final CashierDashboard view;

    public CashierDashboardController() {
        this.view = new CashierDashboard();

        view.setVisible(true);
    }

    public static void main(String[] args) {
        new CashierDashboardController();
    }
}
