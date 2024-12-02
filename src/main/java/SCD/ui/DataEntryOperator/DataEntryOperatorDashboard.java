

package SCD.ui.DataEntryOperator;

import SCD.ui.SuperAdmin.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataEntryOperatorDashboard extends JFrame {

    private DEOSidebar sidebar;
    private JPanel mainContent;
    private boolean isSidebarVisible = true;

    public DataEntryOperatorDashboard() {
        setTitle("Data Entry Operator Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebar = new DEOSidebar();
        add(sidebar, BorderLayout.WEST);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        JPanel toggleBar = createToggleBar();
        contentPanel.add(toggleBar, BorderLayout.NORTH);

        mainContent = createMainContent();
        contentPanel.add(mainContent, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
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

        mainContent.add(createCard("View Vendor Info", "C:\\Users\\AMMAR\\Desktop\\icons\\enter_data.png", this::openViewVendorsPage));
        mainContent.add(createCard("Add New Vendor", "C:\\Users\\AMMAR\\Desktop\\icons\\update.png", this::openAddNewVendorPage));
        mainContent.add(createCard("Add Products", "C:\\Users\\AMMAR\\Desktop\\icons\\reports.png", this::openAddProductsPage));
        mainContent.add(createCard("View Products", "C:\\Users\\AMMAR\\Desktop\\icons\\pending.png", this::openViewProductsPage));
        mainContent.add(createCard("Remove a Product", "C:\\Users\\AMMAR\\Desktop\\icons\\pending.png", e -> {}));
        mainContent.add(createCard("Remove a vendor", "C:\\Users\\AMMAR\\Desktop\\icons\\pending.png", e -> {}));

        return mainContent;
    }

    private void openViewVendorsPage(ActionEvent e) {
        new ViewVendorsPage();
        dispose();
    }

    private void openAddNewVendorPage(ActionEvent e) {
        new AddNewVendorPage();
        dispose();
    }

    private void openAddProductsPage(ActionEvent e) {
        new AddProductPage();
        dispose();
    }

    private void openViewProductsPage(ActionEvent e) {
        new ViewProductsPage();
        dispose();
    }

    private JPanel createCard(String title, String iconPath, ActionListener actionListener) {
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
        cardTitle.setForeground(new Color(0, 0, 0));
        card.add(cardTitle, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        });

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataEntryOperatorDashboard dashboard = new DataEntryOperatorDashboard();
            dashboard.setVisible(true);
        });
    }
}
