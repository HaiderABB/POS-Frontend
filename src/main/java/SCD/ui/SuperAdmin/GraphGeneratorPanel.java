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

public class GraphGeneratorPanel extends JFrame {

    public GraphGeneratorPanel(String graphType, String duration, String branchCode, String startDate, String endDate) {
        setTitle(graphType + " Report");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(4, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(graphType + " Report", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);

        JLabel branchCodeLabel = new JLabel("Branch Code: " + branchCode, SwingConstants.CENTER);
        branchCodeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(branchCodeLabel);

        JLabel durationLabel = new JLabel("Duration: " + duration, SwingConstants.CENTER);
        durationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(durationLabel);

        JLabel dateRangeLabel = new JLabel("Date Range: " + startDate + " to " + endDate, SwingConstants.CENTER);
        dateRangeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(dateRangeLabel);

        add(headerPanel, BorderLayout.NORTH);

        DefaultCategoryDataset dataset = generateDataset(graphType, duration, startDate, endDate);


        JFreeChart barChart = ChartFactory.createBarChart(
                graphType + " Report", "Time Period", graphType + " Amount", dataset);
        customizeChart(barChart);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(750, 450));

        add(chartPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private DefaultCategoryDataset generateDataset(String graphType, String duration, String startDate, String endDate) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        LocalDate now = LocalDate.now();

        switch (duration.toLowerCase()) {
            case "monthly":
                for (int i = 1; i <= now.getDayOfMonth(); i++) {
                    dataset.addValue(150 + i * 10, graphType, "Day " + i);
                }
                break;

            case "yearly":
                for (int i = 1; i <= now.getMonthValue(); i++) {
                    dataset.addValue(500 + i * 50, graphType, LocalDate.of(now.getYear(), i, 1).getMonth().name());
                }
                break;

            case "weekly":
                LocalDate startOfWeek = now.minusDays(now.getDayOfWeek().getValue() - 1); // Start of the current week
                for (int i = 0; i < 7; i++) {
                    LocalDate day = startOfWeek.plusDays(i);
                    dataset.addValue(200 + i * 20, graphType, day.getDayOfWeek().name());
                }
                break;

            case "custom":
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);
                long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1;

                for (int i = 0; i < daysBetween; i++) {
                    LocalDate day = start.plusDays(i);
                    dataset.addValue(300 + i * 15, graphType, day.toString());
                }
                break;

            default:
                dataset.addValue(1000, graphType, "Today");
                break;
        }

        return dataset;
    }


    private void customizeChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(100, 150, 250));
        renderer.setDrawBarOutline(true);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setLabelFont(new Font("Arial", Font.BOLD, 12));
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 10));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 12));

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);
    }
}
