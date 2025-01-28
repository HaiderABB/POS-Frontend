package SCD.controllers.CashierControllers;


import SCD.ui.Cashier.CashierDashboard;

import javax.swing.*;

public class CashierController {

    private CashierDashboard dashboard;

    public CashierController() {
        dashboard = new CashierDashboard();
        initialize();
    }

    private void initialize() {
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CashierController::new);
    }
}
