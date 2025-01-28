package SCD.ui.DataEntryOperator;

import SCD.controllers.DataEntryOperatorControllers.DEOSidebarController;

import javax.swing.*;
import SCD.ui.Common.Props;
import java.awt.*;

public class DataEntryOperatorDashboard extends JFrame implements Props {

    private JPanel contentPanel;

    public DataEntryOperatorDashboard() {
        setTitle("Data Entry Operator Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // scd- proj initInitialize Sidebar
        DEOSidebarController sidebarController = new DEOSidebarController(this);
        JPanel sidebar = sidebarController.getView();
        add(sidebar, BorderLayout.WEST);

        // scd- proj initContent Panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JLabel instructionsLabel = new JLabel(
                "<html><h1>Welcome to the Data Entry Operator Dashboard</h1>" +
                        "<p>Use the sidebar to navigate to different modules.</p></html>");
        instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(instructionsLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
