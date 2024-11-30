

package SCD.ui.DataEntryOperator;

import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DataEntryOperatorDashboard extends JFrame {

    private Sidebar sidebar;
    private JPanel mainContent;
    private boolean isSidebarVisible = true;

    public DataEntryOperatorDashboard() {
        setTitle("Data Entry Operator Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] menuItems = {"Dashboard", "Enter Data", "Update Records", "View Reports", "Pending Entries", "Logout"};
        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JPanel toggleBar = createToggleBar();
        contentPanel.add(toggleBar, BorderLayout.NORTH);

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createToggleBar() {
        JPanel toggleBar = new JPanel(new BorderLayout());
        toggleBar.setPreferredSize(new Dimension(1000, 50));
        toggleBar.setBackground(new Color(240, 240, 240));

        JButton toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 18));
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(Color.WHITE);
        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleButton.addActionListener(this::toggleSidebar);

        toggleBar.add(toggleButton, BorderLayout.WEST);

        return toggleBar;
    }

    private void toggleSidebar(ActionEvent e) {
        isSidebarVisible = !isSidebarVisible;
        sidebar.setVisible(isSidebarVisible);
        revalidate();
        repaint();
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new GridLayout(2, 2, 20, 20));
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainContent.setBackground(Color.WHITE);

        mainContent.add(createCard("Enter Data", "C:\\Users\\AMMAR\\Desktop\\icons\\enter_data.png"));
        mainContent.add(createCard("Update Records", "C:\\Users\\AMMAR\\Desktop\\icons\\update.png"));
        mainContent.add(createCard("View Reports", "C:\\Users\\AMMAR\\Desktop\\icons\\reports.png"));
        mainContent.add(createCard("Pending Entries", "C:\\Users\\AMMAR\\Desktop\\icons\\pending.png"));

        return mainContent;
    }

    private JPanel createCard(String title, String iconPath) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel icon = new JLabel(new ImageIcon(iconPath));
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(icon, BorderLayout.CENTER);

        JLabel cardTitle = new JLabel(title, JLabel.CENTER);
        cardTitle.setFont(new Font("Arial", Font.BOLD, 16));
        cardTitle.setForeground(new Color(102, 153, 255));
        card.add(cardTitle, BorderLayout.SOUTH);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataEntryOperatorDashboard dashboard = new DataEntryOperatorDashboard();
            dashboard.setVisible(true);
        });
    }
}
