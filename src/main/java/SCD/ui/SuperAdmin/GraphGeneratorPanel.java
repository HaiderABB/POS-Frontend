package SCD.ui.SuperAdmin;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GraphGeneratorPanel extends JFrame {
    private String graphType;
    private Map<LocalDate, Double> data;

    public GraphGeneratorPanel(LocalDate startDate, LocalDate endDate, String graphType, Map<LocalDate, Double> data) {
        this.graphType = graphType;
        this.data = data;

        setTitle(graphType + " Graph");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel(graphType + " from " + startDate + " to " + endDate);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(infoLabel, BorderLayout.NORTH);

        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBarGraph((Graphics2D) g);
            }
        };
        add(graphPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private void drawBarGraph(Graphics2D g) {
        int padding = 70;
        int labelPadding = 30;
        int width = getWidth();
        int height = getHeight();
        int graphWidth = width - 2 * padding;
        int graphHeight = height - 2 * padding;

        // Calculate max value
        double maxValue = data.values().stream().max(Double::compareTo).orElse(0.0);

        // Background and border
        g.setColor(new Color(245, 245, 245));
        g.fillRect(0, 0, width, height);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(padding, padding, graphWidth, graphHeight);

        // Draw grid lines
        int gridCount = 5;
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= gridCount; i++) {
            int y = padding + i * (graphHeight / gridCount);
            g.drawLine(padding, y, padding + graphWidth, y); // Horizontal grid
            g.drawString(String.format("%.1f", maxValue - (i * maxValue / gridCount)), padding - 50, y + 5);
        }

        // Draw axes
        g.setColor(Color.BLACK);
        g.drawLine(padding, padding, padding, padding + graphHeight); // Y-axis
        g.drawLine(padding, padding + graphHeight, padding + graphWidth, padding + graphHeight); // X-axis

        // Bar colors based on graph type
        Color barColor = switch (graphType) {
            case "Sales" -> new Color(70, 130, 180);
            case "Remaining Stock" -> new Color(60, 179, 113);
            case "Profit" -> new Color(255, 165, 0);
            default -> Color.BLUE;
        };

        // Draw bars and labels
        int barWidth = graphWidth / data.size() - 10; // Add spacing between bars
        int index = 0;
        for (Map.Entry<LocalDate, Double> entry : data.entrySet()) {
            LocalDate date = entry.getKey();
            double value = entry.getValue();

            int barHeight = (int) ((value / maxValue) * graphHeight);
            int x = padding + index * (barWidth + 10) + 10; // Add space between bars
            int y = padding + graphHeight - barHeight;

            // Draw bar
            GradientPaint gradient = new GradientPaint(x, y, barColor.brighter(), x, y + barHeight, barColor.darker());
            g.setPaint(gradient);
            g.fillRect(x, y, barWidth, barHeight);

            // Draw bar outline
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);

            // Draw value above bar
            String valueLabel = String.format("%.2f", value);
            g.drawString(valueLabel, x + barWidth / 2 - g.getFontMetrics().stringWidth(valueLabel) / 2, y - 5);

            // Draw date label below bar
            String dateLabel = date.toString();
            g.drawString(dateLabel, x + barWidth / 2 - g.getFontMetrics().stringWidth(dateLabel) / 2, padding + graphHeight + labelPadding);

            index++;
        }
    }

    // Example dummy data generator
    public static Map<LocalDate, Double> getDummyData(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Double> dummyData = new HashMap<>();
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {
            dummyData.put(current, Math.random() * 10000); // Random values between 0 and 10,000
            current = current.plusDays(1); // Increment day by day
        }

        return dummyData;
    }

    public static void main(String[] args) {
        // Test graph with dummy data
        LocalDate startDate = LocalDate.of(2024, 12, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 5);

        GraphGeneratorPanel salesGraph = new GraphGeneratorPanel(startDate, endDate, "Sales", getDummyData(startDate, endDate));
        salesGraph.setVisible(true);

        GraphGeneratorPanel stockGraph = new GraphGeneratorPanel(startDate, endDate, "Remaining Stock", getDummyData(startDate, endDate));
        stockGraph.setVisible(true);

        GraphGeneratorPanel profitGraph = new GraphGeneratorPanel(startDate, endDate, "Profit", getDummyData(startDate, endDate));
        profitGraph.setVisible(true);
    }
}
