package SCD.ui.SuperAdmin;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReportGeneratorPanel extends JFrame {

    public ReportGeneratorPanel(String branchCode, String duration, String startDate, String endDate) {
        setTitle("Report Generator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea reportDetails = new JTextArea();
        reportDetails.setEditable(false);

        // Display branch code, duration, and date range
        reportDetails.append("Branch Code: " + branchCode + "\n");
        reportDetails.append("Duration: " + duration + "\n");
        reportDetails.append("Start Date: " + startDate + "\n");
        reportDetails.append("End Date: " + endDate + "\n");

        // Generate and display simulated report data
        String simulatedData = generateSimulatedReportData(branchCode, duration, startDate, endDate);
        reportDetails.append("\n\nSimulated Report Data:\n");
        reportDetails.append(simulatedData);

        reportDetails.setFont(new Font("Arial", Font.PLAIN, 16));
        reportDetails.setMargin(new Insets(10, 10, 10, 10));
        reportDetails.setBackground(new Color(230, 230, 250));

        add(new JScrollPane(reportDetails), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private String generateSimulatedReportData(String branchCode, String duration, String startDate, String endDate) {
        Map<String, Integer> data = generateDummyData(branchCode);
        StringBuilder report = new StringBuilder();

        report.append("Total Sales: $").append(data.get("sales")).append("\n");
        report.append("Total Profit: $").append(data.get("profit")).append("\n");
        report.append("Remaining Stock: ").append(data.get("stock")).append(" Units\n");

        report.append("\nDuration Details:\n");
        report.append("Start Date: ").append(startDate).append("\n");
        report.append("End Date: ").append(endDate).append("\n");

        return report.toString();
    }

    private Map<String, Integer> generateDummyData(String branchCode) {
        int baseSales = 10000 + branchCode.hashCode() % 5000;
        int baseProfit = 2500 + branchCode.hashCode() % 1000;
        int remainingStock = 150 + branchCode.hashCode() % 50;

        Map<String, Integer> data = new HashMap<>();
        data.put("sales", baseSales);
        data.put("profit", baseProfit);
        data.put("stock", remainingStock);

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportGeneratorPanel(
                "BR-1234", "Weekly", "2024-12-01", "2024-12-07").setVisible(true));
    }
}
