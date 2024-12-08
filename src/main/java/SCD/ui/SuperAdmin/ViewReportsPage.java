package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ViewReportsPage extends JFrame {
    private Sidebar sidebar;
    private NavBar navBar;
    private JPanel datePanel;

    public ViewReportsPage() {
        setTitle("View Reports");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Reports");

        JPanel reportSelectionPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        reportSelectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel reportDurationLabel = new JLabel("Select Report Duration:");
        String[] reportDurations = {"Today", "Weekly", "Monthly", "Yearly", "Custom Range"};
        JComboBox<String> reportDurationComboBox = new JComboBox<>(reportDurations);

        JLabel branchCodeLabel = new JLabel("Branch Code (Required, BR-1234):");
        JTextField branchCodeField = new JTextField();

        JLabel graphTypeLabel = new JLabel("Select Graph Type:");
        String[] graphTypes = {"Sales", "Remaining Stock", "Profit"};
        JComboBox<String> graphTypeComboBox = new JComboBox<>(graphTypes);

        JLabel startDateLabel = new JLabel("Date From:");
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));
        startDateSpinner.setEnabled(false);

        JLabel endDateLabel = new JLabel("Date To:");
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
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
        JButton generateReportButton = ButtonFactory.createStyledButton("Generate Report");
        JButton generateGraphButton = ButtonFactory.createStyledButton("Generate Graph");
        buttonPanel.add(generateReportButton);
        buttonPanel.add(generateGraphButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(datePanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.add(reportSelectionPanel, BorderLayout.CENTER);
        contentPanel.add(southPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        reportDurationComboBox.addActionListener(e -> {
            String selectedDuration = (String) reportDurationComboBox.getSelectedItem();
            boolean isCustomRange = "Custom Range".equals(selectedDuration);
            datePanel.setVisible(isCustomRange);
            startDateSpinner.setEnabled(isCustomRange);
            endDateSpinner.setEnabled(isCustomRange);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        generateReportButton.addActionListener(e -> {
            String selectedDuration = (String) reportDurationComboBox.getSelectedItem();
            String branchCode = branchCodeField.getText().trim();

            LocalDate startDate = null, endDate = null;
            if ("Custom Range".equals(selectedDuration)) {
                startDate = ((Date) startDateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                endDate = ((Date) endDateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            } else {
                startDate = getStartDateForDuration(selectedDuration);
                endDate = LocalDate.now();
            }

            if (!validateBranchCode(branchCode)) {
                JOptionPane.showMessageDialog(ViewReportsPage.this, "Branch Code is required and must follow the format 'BR-1234'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String message = "Generating reports for " + selectedDuration +
                    "\nBranch Code: " + branchCode +
                    "\nDate From: " + startDate +
                    "\nDate To: " + endDate;
            JOptionPane.showMessageDialog(ViewReportsPage.this, message, "Info", JOptionPane.INFORMATION_MESSAGE);

            // Logic to fetch and display reports
        });

        generateGraphButton.addActionListener(e -> {
            String selectedDuration = (String) reportDurationComboBox.getSelectedItem();
            String selectedGraphType = (String) graphTypeComboBox.getSelectedItem();
            LocalDate startDate = null, endDate = null;
            if ("Custom Range".equals(selectedDuration)) {
                startDate = ((Date) startDateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                endDate = ((Date) endDateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            } else {
                startDate = getStartDateForDuration(selectedDuration);
                endDate = LocalDate.now();
            }

            // Open graph generation panel
            // Assuming a method `getGraphData(graphType, startDate, endDate)` returns data for the graph
            Map<LocalDate, Double> graphData = getGraphData(selectedGraphType, startDate, endDate);
            new GraphGeneratorPanel(startDate, endDate, selectedGraphType, graphData).setVisible(true);

        });
    }

    private Map<LocalDate, Double> getGraphData(String graphType, LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Double> data = new HashMap<>();

        switch (graphType) {
            case "Sales":
                // Replace with logic to fetch actual sales data
                data = GraphGeneratorPanel.getDummyData(startDate, endDate);
                break;

            case "Remaining Stock":
                // Replace with logic to fetch actual stock data
                data = GraphGeneratorPanel.getDummyData(startDate, endDate);
                break;

            case "Profit":
                // Replace with logic to fetch actual profit data
                data = GraphGeneratorPanel.getDummyData(startDate, endDate);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Invalid Graph Type selected.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

        return data;
    }

    private boolean validateBranchCode(String branchCode) {
        return branchCode.matches("BR-\\d{4}");
    }

    private LocalDate getStartDateForDuration(String duration) {
        LocalDate now = LocalDate.now();
        switch (duration) {
            case "Today":
                return now;
            case "Weekly":
                return now.minusWeeks(1);
            case "Monthly":
                return now.minusMonths(1);
            case "Yearly":
                return now.minusYears(1);
            default:
                return now;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewReportsPage frame = new ViewReportsPage();
            frame.setVisible(true);
        });
    }
}
