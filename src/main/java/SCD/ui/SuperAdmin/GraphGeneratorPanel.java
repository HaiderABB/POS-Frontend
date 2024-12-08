package SCD.ui.SuperAdmin;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GraphGeneratorPanel extends JFrame {

    public GraphGeneratorPanel(String graphType, String duration, String branchCode, String startDate, String endDate) {
        setTitle(graphType + " Report");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel graphInfo = new JLabel("<html><center>" + graphType + " Report<br>"
                + "Branch Code: " + branchCode + "<br>"
                + "Duration: " + duration + "<br>"
                + "Start Date: " + startDate + "<br>"
                + "End Date: " + endDate + "</center></html>", SwingConstants.CENTER);
        graphInfo.setFont(new Font("Arial", Font.BOLD, 16));
        graphInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Generate dataset based on duration
        DefaultCategoryDataset dataset = generateDataset(graphType, duration, branchCode, startDate, endDate);

        // Create the chart
        JFreeChart barChart = ChartFactory.createBarChart(
                graphType + " Report", // Chart title
                "Time Period",         // X-axis label
                graphType + " Amount", // Y-axis label
                dataset                // Dataset
        );

        // Customize the chart appearance
        customizeChart(barChart);

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(750, 450));

        add(graphInfo, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private DefaultCategoryDataset generateDataset(String graphType, String duration, String branchCode, String startDate, String endDate) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Always parse startDate and endDate as full dates
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        switch (duration.toLowerCase()) {
            case "today":
                dataset.addValue(generateDummyValue(branchCode), graphType, "Today");
                break;

            case "weekly":
                for (int i = 6; i >= 0; i--) {
                    LocalDate day = end.minusDays(i);
                    dataset.addValue(generateDummyValue(branchCode), graphType, day.getDayOfWeek().name());
                }
                break;

            case "monthly":
                for (int i = 1; i <= end.getDayOfMonth(); i++) {
                    dataset.addValue(generateDummyValue(branchCode), graphType, "Day " + i);
                }
                break;

            case "yearly":
                for (int i = 1; i <= 12; i++) {
                    String monthName = LocalDate.of(start.getYear(), i, 1).getMonth().name();
                    dataset.addValue(generateDummyValue(branchCode), graphType, monthName.substring(0, 3)); // Shortened month name
                }
                break;

            case "custom range":
                long days = ChronoUnit.DAYS.between(start, end) + 1;
                for (int i = 0; i < days; i++) {
                    LocalDate day = start.plusDays(i);
                    dataset.addValue(generateDummyValue(branchCode), graphType, day.toString());
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid duration: " + duration);
        }

        return dataset;
    }
    private String formatDate(LocalDate date, String duration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Default format
        return date.format(formatter);
    }


    private int generateDummyValue(String branchCode) {
        return 100 + Math.abs(branchCode.hashCode() % 500);
    }

    private void customizeChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();

        // Customize the renderer (bar colors)
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(100, 150, 250)); // Blue bars
        renderer.setDrawBarOutline(true);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);

        // Customize the axis
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLabelFont(new Font("Arial", Font.BOLD, 12));
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 10));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 12));

        // Customize background
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphGeneratorPanel panel = new GraphGeneratorPanel(
                    "Sales", "Weekly", "BR-1234", "2024-12-01", "2024-12-07");
            panel.setVisible(true);
        });
    }
}
