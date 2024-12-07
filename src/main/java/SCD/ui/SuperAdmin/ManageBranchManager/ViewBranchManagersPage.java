package SCD.ui.SuperAdmin.ManageBranchManager;

import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewBranchManagersPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;

    public ViewBranchManagersPage() {
        setTitle("View Branch Managers");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("View Branch Managers");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Branch Manager Code", "Name", "Email", "Branch Code"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable branchManagerTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(branchManagerTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        loadSampleData();
    }

    private void loadSampleData() {
        tableModel.addRow(new Object[]{"BM-0001", "John Doe", "john.doe@example.com", "BH-1234"});
        tableModel.addRow(new Object[]{"BM-0002", "Jane Smith", "jane.smith@example.com", "BH-5678"});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewBranchManagersPage frame = new ViewBranchManagersPage();
            frame.setVisible(true);
        });
    }
}
