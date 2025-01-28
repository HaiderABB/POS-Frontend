package SCD.ui.SuperAdmin;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import SCD.ui.Common.Props;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import SCD.model.models.Sale;
import SCD.model.service.Json.GetReportJSON;

public class GraphGeneratorPanel extends JFrame implements Props {

    String graphType;
    GetReportJSON json;

    public GraphGeneratorPanel(String graphType, String duration, String branchCode, String startDate, String endDate,
            GetReportJSON json) {
        this.graphType = graphType;
        this.json = json;
        setTitle(graphType + " Report");
        setSize(1350, 600);
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

        if (graphType.equals("Sales")) {
            if (json.getSalesData() == null) {
                JOptionPane.showMessageDialog(this, "No sales data available for the selected date range.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            generateSalesBarChart(duration, startDate, endDate);
        }
        if (graphType.equals("Profit")) {
            if (json.getSalesData() == null) {
                JOptionPane.showMessageDialog(this, "No profit data available for the selected date range.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            generateProfitBarChart(duration, startDate, endDate);
        }

    }

    private void generateSalesBarChart(String duration, String startDate, String endDate) {

        if (duration.equals("Daily")) {
            generateDailySales();
        }
        if (duration.equals("Weekly")) {
            generateWeeklySales();
        }
        if (duration.equals("Monthly")) {
            generateMonthlySales();
        }
        if (duration.equals("Yearly")) {
            generateYearlySales();
        }

    }

    private void generateProfitBarChart(String duration, String startDate, String endDate) {
        if (duration.equals("Daily")) {
            generateDailyProfit();
        }
        if (duration.equals("Weekly")) {
            generateWeeklyProfit();
        }
        if (duration.equals("Monthly")) {
            generateMonthlyProfit();
        }
        if (duration.equals("Yearly")) {
            generateYearlyProfit();
        }
    }

    public void generateDailySales() {
        Map<Integer, Integer> salesData = new TreeMap<>();

        for (int i = 0; i < 24; i++) {
            salesData.put(i, salesPerHour(i));
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Integer> entry : salesData.entrySet()) {
            int hour1 = entry.getKey();
            hour1 = hour1 + 1;
            String hour = hour1 + ":00";
            dataset.addValue(entry.getValue(), "Sales", hour);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Sales Per Hour",
                "Hour",
                "Sales",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        add(chartPanel, BorderLayout.CENTER);

    }

    public void generateWeeklySales() {

        Map<String, Integer> weeklySales = new HashMap<>();

        weeklySales.put("Monday", salesPerDay("MONDAY"));
        weeklySales.put("Tuesday", salesPerDay("TUESDAY"));
        weeklySales.put("Wednesday", salesPerDay("WEDNESDAY"));
        weeklySales.put("Thursday", salesPerDay("THURSDAY"));
        weeklySales.put("Friday", salesPerDay("FRIDAY"));
        weeklySales.put("Saturday", salesPerDay("SATURDAY"));
        weeklySales.put("Sunday", salesPerDay("SUNDAY"));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : weeklySales.entrySet()) {
            dataset.addValue(entry.getValue(), "Sales", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Weekly Sales",
                "Days",
                "Number of sales",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);

    }

    public void generateMonthlySales() {

        LocalDate rn = LocalDate.now();

        int year = rn.getYear();
        int month = rn.getMonthValue();

        YearMonth yearMonth = YearMonth.of(year, month);

        int totalDays = yearMonth.lengthOfMonth();

        Map<Integer, Integer> dailySales = new HashMap<>();

        for (int day = 1; day <= totalDays; day++) {
            int sales = salesPerDay(day);
            dailySales.put(day, sales);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<Integer, Integer> entry : dailySales.entrySet()) {
            dataset.addValue(entry.getValue(), "Sales", "Day " + entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Monthly Sales",
                "Days of the Month",
                "Number of sales",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        CategoryAxis xAxis = barChart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel chartPanel = new ChartPanel(barChart);

        add(chartPanel, BorderLayout.CENTER);
    }

    public void generateMonthlyProfit() {

        LocalDate rn = LocalDate.now();

        int year = rn.getYear();
        int month = rn.getMonthValue();

        YearMonth yearMonth = YearMonth.of(year, month);

        int totalDays = yearMonth.lengthOfMonth();

        Map<Integer, Double> dailyProfit = new HashMap<>();

        for (int day = 1; day <= totalDays; day++) {
            double sales = profitPerDay(day);
            dailyProfit.put(day, sales);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<Integer, Double> entry : dailyProfit.entrySet()) {
            dataset.addValue(entry.getValue(), "Profit", "Day " + entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Monthly Profit",
                "Days of the Month",
                "Total Profit",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        CategoryAxis xAxis = barChart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel chartPanel = new ChartPanel(barChart);

        add(chartPanel, BorderLayout.CENTER);

    }

    public void generateDailyProfit() {

        Map<Integer, Double> profitData = new TreeMap<>();

        for (int i = 0; i < 24; i++) {
            profitData.put(i, profitPerHour(graphType, i));
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Double> entry : profitData.entrySet()) {
            int hour1 = entry.getKey();
            hour1 = hour1 + 1;
            String hour = hour1 + ":00";
            dataset.addValue(entry.getValue(), "Profit", hour);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Profit Per Hour",
                "Hour",
                "Profit",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        add(chartPanel, BorderLayout.CENTER);
    }

    public void generateWeeklyProfit() {

        Map<String, Double> weeklyProfit = new HashMap<>();

        weeklyProfit.put("Monday", profitPerDay("MONDAY"));
        weeklyProfit.put("Tuesday", profitPerDay("TUESDAY"));
        weeklyProfit.put("Wednesday", profitPerDay("WEDNESDAY"));
        weeklyProfit.put("Thursday", profitPerDay("THURSDAY"));
        weeklyProfit.put("Friday", profitPerDay("FRIDAY"));
        weeklyProfit.put("Saturday", profitPerDay("SATURDAY"));
        weeklyProfit.put("Sunday", profitPerDay("SUNDAY"));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : weeklyProfit.entrySet()) {
            dataset.addValue(entry.getValue(), "Profit", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Weekly Profit",
                "Days",
                "Total Profit",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public void generateYearlySales() {

        Map<Integer, Integer> monthlySales = new HashMap<>();

        for (int month = 1; month <= 12; month++) {

            monthlySales.put(month, salesPerMonth(month));

        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Integer> entry : monthlySales.entrySet()) {
            dataset.addValue(entry.getValue(), "Sales", "Month " + entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Yearly Sales",
                "Months",
                "Total Sales",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        CategoryAxis xAxis = barChart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel, BorderLayout.CENTER);

    }

    public void generateYearlyProfit() {
        Map<Integer, Double> monthlyProfit = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            monthlyProfit.put(month, profitPerMonth(month));
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Double> entry : monthlyProfit.entrySet()) {
            dataset.addValue(entry.getValue(), "Profit", "Month " + entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Yearly Profit",
                "Months",
                "Total Profit",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        CategoryAxis xAxis = barChart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        ChartPanel chartPanel = new ChartPanel(barChart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public int salesPerMonth(int month) {
        int total = 0;

        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            int saleMonth = saleTime.getMonthValue();
            if (saleMonth == month) {
                {
                    total += 1;
                }
            }
        }
        return total;
    }

    public double profitPerMonth(int month) {
        double total = 0.0;
        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            int saleMonth = saleTime.getMonthValue();
            if (saleMonth == month) {
                total += sale.getProfit();
            }

        }
        return total;
    }

    public int salesPerHour(int hour) {

        int total = 0;

        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            int saleHour = saleTime.getHour();
            if (saleHour == hour) {
                total += 1;
            }
        }

        return total;

    }

    public double profitPerHour(String type, int hour) {

        double total = 0.0;

        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            int saleHour = saleTime.getHour();
            if (saleHour == hour) {
                total += sale.getProfit();
            }
        }

        return total;

    }

    public int salesPerDay(int day) {
        int total = 0;
        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            int saleDay = saleTime.getDayOfMonth();
            if (saleDay == day) {
                total += 1;
            }
        }
        return total;
    }

    public double profitPerDay(int day) {
        double total = 0.0;
        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            int saleDay = saleTime.getDayOfMonth();
            if (saleDay == day) {
                total += sale.getProfit();
            }
        }
        return total;
    }

    public int salesPerDay(String day) {
        int total = 0;
        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            DayOfWeek saleDay = saleTime.getDayOfWeek();
            if (saleDay.name().equals(day)) {
                total += 1;
            }
        }
        return total;

    }

    public double profitPerDay(String day) {
        double total = 0.0;
        List<Sale> sales = json.getSalesData();
        for (Sale sale : sales) {
            LocalDateTime saleTime = sale.getCreatedAt();
            DayOfWeek saleDay = saleTime.getDayOfWeek();
            if (saleDay.name().equals(day)) {
                total += sale.getProfit();
                System.out.println(sale.getProfit());
            }
        }
        System.out.println(total);
        return total;

    }

    public static void main(String[] args) {
        List<Sale> sales = new ArrayList<>();

        Sale sale1 = new Sale(null, null, 0.0, 24);
        Sale sale2 = new Sale(null, null, 0.0, 48);
        Sale sale3 = new Sale(null, null, 0.0, 72);
        Sale sale4 = new Sale(null, null, 0.0, 96);
        Sale sale5 = new Sale(null, null, 0.0, 120);
        Sale sale6 = new Sale(null, null, 0.0, 24);

        sales.add(sale6);
        sales.add(sale5);
        sales.add(sale4);
        sales.add(sale3);
        sales.add(sale2);
        sales.add(sale1);

        GetReportJSON json = new GetReportJSON(sales, null, 0.0, "");

        GraphGeneratorPanel graphGeneratorPanel = new GraphGeneratorPanel("Profit", "Yearly", "BR-0001", "2023-01-01",
                "2023-01-01", json);
        graphGeneratorPanel.setVisible(true);
    }

}
