package SCD.ui.SuperAdmin.ManageBranchManager;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import SCD.model.models.Employee;
import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class ViewBranchManagersPage extends JFrame {

    private Sidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;
    private JTable branchManagerTable;
    Employee employee;

    public ViewBranchManagersPage(Employee employee) {
        this.employee = employee;
        setTitle("View Branch Managers");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar(employee);
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("View Branch Managers");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Branch Manager Code", "Name", "Email", "Branch Code" };
        tableModel = new DefaultTableModel(columnNames, 0);
        branchManagerTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(branchManagerTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getBranchManagerTable() {
        return branchManagerTable;
    }
}
