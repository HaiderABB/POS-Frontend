package SCD.ui.SuperAdmin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SCD.controllers.SuperAdminControllers.SidebarController;
import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Props;

public class Sidebar extends JPanel implements Props {

    private final SidebarController controller;

    public Sidebar() {

        controller = new SidebarController(this);

        setPreferredSize(new Dimension(240, 800));
        setBackground(Props.bg);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("path/to/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);

        add(createButton("Dashboard", controller::openDashboard));
        add(createButton("Add Branch", controller::openAddBranchPage));
        add(createButton("Update Branch", controller::openUpdateBranchPage));
        add(createButton("Delete Branch", controller::openDeleteBranchPage));
        add(createButton("View Branches", controller::openViewBranchesPage));
        add(createButton("Add Branch Manager", controller::openAddBranchManagerPage));
        add(createButton("Update Branch Manager", controller::openUpdateBranchManagerPage));
        add(createButton("Delete Branch Manager", controller::openDeleteBranchManagerPage));
        add(createButton("View Branch Managers", controller::openViewBranchManagersPage));
        add(createButton("View Reports", controller::openReportsPage));
        add(createButton("System Settings", controller::openSystemSettingsPage));
        add(createButton("Logout", controller::performLogout));
    }

    private JButton createButton(String label, Runnable action) {
        JButton button = ButtonFactory.createStyledButton(label);
        button.addActionListener(e -> action.run());
        return button;
    }
}
