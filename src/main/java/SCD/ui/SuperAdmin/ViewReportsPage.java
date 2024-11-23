package SCD.ui.SuperAdmin;

import SCD.ui.Common.ButtonFactory;
import SCD.ui.Common.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ViewReportsPage extends JFrame {
    private Sidebar sidebar;

    public ViewReportsPage() {
        setTitle("View Reports");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] menuItems = {"Dashboard", "Manage Users", "Manage Branches", "View Reports", "System Settings", "Logout"};
        sidebar = new Sidebar(menuItems, "C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\companyLogo.png");
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("View Reports", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel reportSelectionPanel = new JPanel();
        reportSelectionPanel.setLayout(new GridLayout(5, 3, 10, 10));
        reportSelectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel reportTypeLabel = new JLabel("Select Report Type:");
        String[] reportTypes = {"Sales Report", "Inventory Report"};
        JComboBox<String> reportTypeComboBox = new JComboBox<>(reportTypes);

        JLabel startDateLabel = new JLabel("Start Date:");
        JSpinner startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));

        JLabel endDateLabel = new JLabel("End Date:");
        JSpinner endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));

        reportSelectionPanel.add(reportTypeLabel);
        reportSelectionPanel.add(reportTypeComboBox);
        reportSelectionPanel.add(startDateLabel);
        reportSelectionPanel.add(startDateSpinner);
        reportSelectionPanel.add(endDateLabel);
        reportSelectionPanel.add(endDateSpinner);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JButton generateReportButton = ButtonFactory.createStyledButton("Generate Report");

        buttonPanel.add(generateReportButton);

        contentPanel.add(reportSelectionPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedReport = (String) reportTypeComboBox.getSelectedItem();
                Date startDate = (Date) startDateSpinner.getValue();
                Date endDate = (Date) endDateSpinner.getValue();

                if (selectedReport != null && startDate != null && endDate != null) {
                    JOptionPane.showMessageDialog(ViewReportsPage.this,
                            "Generating " + selectedReport + " from " + startDate + " to " + endDate,
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ViewReportsPage.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewReportsPage frame = new ViewReportsPage();
            frame.setVisible(true);
        });
    }
}
