package SCD.ui.SuperAdmin.ManageBranches;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import SCD.ui.Common.Props;

import SCD.ui.Common.NavBar;
import SCD.ui.SuperAdmin.Sidebar;

public class ViewBranchesPage extends JFrame implements Props {
    private Sidebar sidebar;
    private NavBar navBar;
    private DefaultTableModel tableModel;
    private JTable branchTable;

    public ViewBranchesPage() {
        sidebar = new Sidebar();

        setTitle("View Branches");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        navBar = new NavBar();
        navBar.setTitle("View Branches");
        contentPanel.add(navBar, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = { "Branch Code", "Branch Name", "City", "Phone", "Address", "Active" };
        tableModel = new DefaultTableModel(columnNames, 0); // scd- proj initUse 0 to ensure no duplicate rows
        branchTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(branchTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(tablePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void addRow(Object[] rowData) {
        if (!isDuplicate(rowData)) {
            tableModel.addRow(rowData);
        }
    }

    private boolean isDuplicate(Object[] rowData) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean isDuplicate = true;
            for (int j = 0; j < rowData.length; j++) {
                if (!tableModel.getValueAt(i, j).equals(rowData[j])) {
                    isDuplicate = false;
                    break;
                }
            }
            if (isDuplicate) {
                return true;
            }
        }
        return false;
    }
}
