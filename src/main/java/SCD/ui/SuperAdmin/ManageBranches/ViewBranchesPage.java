package SCD.ui.SuperAdmin.ManageBranches;

import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewBranchesPage extends JFrame {
    private Sidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;

    public ViewBranchesPage() {
        setTitle("View Branches");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        // NavBar
        navBar = new NavBar();
        navBar.setTitle("View Branches");
        contentPanel.add(navBar, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Branch ID", "Branch Name", "City", "Phone", "Address", "Active"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable branchTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(branchTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        // Sample data (can be replaced with data retrieval logic)
        addSampleData();
    }

    private void addSampleData() {
        tableModel.addRow(new Object[]{1, "Main Branch", "New York", "0321-1234567", "123 Street", true});
        tableModel.addRow(new Object[]{2, "Second Branch", "Los Angeles", "0321-7654321", "456 Avenue", false});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewBranchesPage frame = new ViewBranchesPage();
            frame.setVisible(true);
        });
    }
}
