package SCD.ui.BranchManager;

import SCD.controllers.BranchManagerControllers.BranchSidebarController;
import SCD.ui.Common.ButtonFactory;

import SCD.ui.Common.Props;
import javax.swing.*;
import java.awt.*;

public class BranchSidebar extends JPanel implements Props {

    private final BranchSidebarController controller;

    public BranchSidebar() {
        controller = new BranchSidebarController(this);

        setPreferredSize(new Dimension(240, 700));
        setBackground(Props.bg);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        add(createButton("Dashboard", controller::openDashboard));

        add(createButton("Add Cashier", controller::openAddCashier));
        add(createButton("Update Cashier", controller::openUpdateCashier));
        add(createButton("Delete Cashier", controller::openDeleteCashier));
        add(createButton("View Cashiers", controller::openViewCashiers));

        add(createButton("Add Data Entry Operator", controller::openAddDataEntryOperator));
        add(createButton("Update Data Entry Operator", controller::openUpdateDataEntryOperator));
        add(createButton("Delete Data Entry Operator", controller::openDeleteDataEntryOperator));
        add(createButton("View Data Entry Operators", controller::openViewDataEntryOperators));
        add(createButton("View Sales", controller::openViewSales));
        add(createButton("Settings", controller::openSettings));
        add(createButton("Logout", controller::performLogout));
    }

    private JButton createButton(String label, Runnable action) {
        JButton button = ButtonFactory.createStyledButton(label);
        button.addActionListener(e -> action.run());
        return button;
    }
}
