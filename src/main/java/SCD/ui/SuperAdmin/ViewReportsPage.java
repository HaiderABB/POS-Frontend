package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ViewReportsPage extends JFrame {
    private JPanel datePanel;

    public ViewReportsPage() {
        setTitle("View Reports");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar (dummy, replace with actual implementation if needed)
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 700));
        sidebar.setBackground(Color.LIGHT_GRAY);
        add(sidebar, BorderLayout.WEST);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        NavBar navBar = new NavBar();
        contentPanel.add(navBar, BorderLayout.NORTH);
        navBar.setTitle("Reports");

        JPanel reportSelectionPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        reportSelectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel reportDurationLabel = new JLabel("Select Report Duration:");
        String[] reportDurations = {"Today", "Weekly", "Monthly", "Yearly", "Custom Range"};
        JComboBox<String> reportDurationComboBox = new JComboBox<>(reportDurations);

        JLabel branchCodeLabel = new JLabel("Branch Code:");
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

        // Event Listeners
        reportDurationComboBox.addActionListener(e -> {
            boolean isCustomRange = "Custom Range".equals(reportDurationComboBox.getSelectedItem());
            datePanel.setVisible(isCustomRange);
            startDateSpinner.setEnabled(isCustomRange);
            endDateSpinner.setEnabled(isCustomRange);
        });

        generateReportButton.addActionListener(e -> {
            String branchCode = branchCodeField.getText().trim();
            String duration = (String) reportDurationComboBox.getSelectedItem();

            if (!validateBranchCode(branchCode)) {
                showErrorDialog("Branch Code is required and must follow the format 'BR-XXXX'.");
                return;
            }

            LocalDate startDate = "Custom Range".equals(duration)
                    ? getCustomStartDate(startDateSpinner)
                    : calculateStartDate(duration);

            LocalDate endDate = "Custom Range".equals(duration)
                    ? getCustomEndDate(endDateSpinner)
                    : calculateEndDate(duration);

            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                showErrorDialog("Invalid date range. Ensure the start date is before or equal to the end date.");
                return;
            }

            String formattedStartDate = formatDate(startDate, duration);
            String formattedEndDate = formatDate(endDate, duration);

            new ReportGeneratorPanel(branchCode, duration, formattedStartDate, formattedEndDate).setVisible(true);
        });

        generateGraphButton.addActionListener(e -> {
            String graphType = (String) graphTypeComboBox.getSelectedItem();
            String duration = (String) reportDurationComboBox.getSelectedItem();
            String branchCode = branchCodeField.getText().trim();

            if (!validateBranchCode(branchCode)) {
                showErrorDialog("Branch Code is required and must follow the format 'BR-XXXX'.");
                return;
            }

            LocalDate startDate = "Custom Range".equals(duration)
                    ? getCustomStartDate(startDateSpinner)
                    : calculateStartDate(duration);

            LocalDate endDate = "Custom Range".equals(duration)
                    ? getCustomEndDate(endDateSpinner)
                    : calculateEndDate(duration);

            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                showErrorDialog("Invalid date range. Ensure the start date is before or equal to the end date.");
                return;
            }

            String formattedStartDate = formatDate(startDate, duration);
            String formattedEndDate = formatDate(endDate, duration);

            new GraphGeneratorPanel(graphType, duration, branchCode, formattedStartDate, formattedEndDate).setVisible(true);
        });
    }

    private LocalDate calculateStartDate(String duration) {
        LocalDate now = LocalDate.now();
        switch (duration) {
            case "Today":
                return now;
            case "Weekly":
                return now.minusDays(6); // Last 7 days including today
            case "Monthly":
                return now.withDayOfMonth(1);
            case "Yearly":
                return now.withDayOfYear(1);
            default:
                return null;
        }
    }

    private LocalDate calculateEndDate(String duration) {
        return LocalDate.now(); // Today is the end date for all predefined durations
    }

    private LocalDate getCustomStartDate(JSpinner spinner) {
        try {
            return ((java.util.Date) spinner.getValue()).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate getCustomEndDate(JSpinner spinner) {
        try {
            return ((java.util.Date) spinner.getValue()).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    private String formatDate(LocalDate date, String duration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if ("Monthly".equals(duration)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        } else if ("Yearly".equals(duration)) {
            formatter = DateTimeFormatter.ofPattern("yyyy");
        }
        return date.format(formatter);
    }

    private boolean validateBranchCode(String branchCode) {
        return branchCode.matches("BR-\\d{4}");
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewReportsPage().setVisible(true));
    }
}
