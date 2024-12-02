package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.NavBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

        JLabel reportTypeLabel = new JLabel("Select Report Type:");
        String[] reportTypes = {"Sales", "Remaining Stock", "Profit"};
        JComboBox<String> reportTypeComboBox = new JComboBox<>(reportTypes);

        JLabel branchCodeLabel = new JLabel("Branch Code (Required, BH-1234):");
        JTextField branchCodeField = new JTextField();


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

        JLabel graphTypeLabel = new JLabel("Select Graph Type:");
        String[] graphTypes = {"None", "Sales", "Remaining Stock", "Profit"};
        JComboBox<String> graphTypeComboBox = new JComboBox<>(graphTypes);

        reportSelectionPanel.add(reportDurationLabel);
        reportSelectionPanel.add(reportDurationComboBox);
        reportSelectionPanel.add(reportTypeLabel);
        reportSelectionPanel.add(reportTypeComboBox);
        reportSelectionPanel.add(branchCodeLabel);
        reportSelectionPanel.add(branchCodeField);
        reportSelectionPanel.add(graphTypeLabel);
        reportSelectionPanel.add(graphTypeComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton generateReportButton = ButtonFactory.createStyledButton("Generate Report");
        buttonPanel.add(generateReportButton);


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

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDuration = (String) reportDurationComboBox.getSelectedItem();
                String selectedReportType = (String) reportTypeComboBox.getSelectedItem();
                String selectedGraphType = (String) graphTypeComboBox.getSelectedItem();
                String branchCode = branchCodeField.getText().trim();

                Date startDate = (Date) startDateSpinner.getValue();
                Date endDate = (Date) endDateSpinner.getValue();

                if (!validateBranchCode(branchCode)) {
                    JOptionPane.showMessageDialog(ViewReportsPage.this, "Branch Code is required and must follow the format 'BH-1234'.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!validateInputs(selectedDuration, startDate, endDate)) {
                    return;
                }

                String message = "Generating " + selectedReportType + " report for " + selectedDuration +
                        "\nBranch Code: " + branchCode +
                        "\nDate From: " + startDate +
                        "\nDate To: " + endDate +
                        "\nGraph Type: " + selectedGraphType;
                JOptionPane.showMessageDialog(ViewReportsPage.this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private boolean validateBranchCode(String branchCode) {
        return branchCode.matches("BH-\\d{4}");
    }

    private boolean validateInputs(String reportDuration, Date startDate, Date endDate) {
        if ("Custom Range".equals(reportDuration) && startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Date From cannot be after Date To.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewReportsPage frame = new ViewReportsPage();
            frame.setVisible(true);
        });
    }
}
