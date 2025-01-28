package SCD.ui.SuperAdmin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import SCD.ui.Common.Props;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

public class ViewReportsPage extends JFrame implements Props {
    public JPanel datePanel;
    private Sidebar sidebar;
    public JComboBox<String> reportDurationComboBox;
    public JTextField branchCodeField;
    public JComboBox<String> graphTypeComboBox;
    public JSpinner startDateSpinner;
    public JSpinner endDateSpinner;
    public JButton generateGraphButton;
    public JButton generateReportButton;

    public ViewReportsPage() {
        setTitle("View Reports");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initSidebar (dummy, replace with actual implementation if needed)

        // scd- proj initContent Panel
        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        NavBar navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Reports");

        JPanel reportSelectionPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        reportSelectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel reportDurationLabel = new JLabel("Select Report Duration:");
        String[] reportDurations = { "Today", "Weekly", "Monthly", "Yearly", "Custom Range" };
        reportDurationComboBox = new JComboBox<>(reportDurations);

        JLabel branchCodeLabel = new JLabel("Branch Code:");
        branchCodeField = new JTextField();

        JLabel graphTypeLabel = new JLabel("Select Graph Type:");
        String[] graphTypes = { "Sales", "Remaining Stock", "Profit" };
        graphTypeComboBox = new JComboBox<>(graphTypes);

        JLabel startDateLabel = new JLabel("Date From:");
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));
        startDateSpinner.setEnabled(false);

        JLabel endDateLabel = new JLabel("Date To:");
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));
        endDateSpinner.setEnabled(false);

        datePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        datePanel.add(startDateLabel);
        datePanel.add(startDateSpinner);
        datePanel.add(endDateLabel);
        datePanel.add(endDateSpinner);
        datePanel.setVisible(false);

        reportSelectionPanel.add(reportDurationLabel);
        reportSelectionPanel.add(reportDurationComboBox);
        reportSelectionPanel.add(branchCodeLabel);
        reportSelectionPanel.add(branchCodeField);
        reportSelectionPanel.add(graphTypeLabel);
        reportSelectionPanel.add(graphTypeComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        generateReportButton = ButtonFactory.createStyledButton("Generate Report");
        generateGraphButton = ButtonFactory.createStyledButton("Generate Graph");
        buttonPanel.add(generateReportButton);
        buttonPanel.add(generateGraphButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(datePanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.add(reportSelectionPanel, BorderLayout.CENTER);
        contentPanel.add(southPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewReportsPage().setVisible(true));
    }
}
