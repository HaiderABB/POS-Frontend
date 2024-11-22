package SCD.ui;

import javax.swing.*;
import java.awt.*;


public class Sidebar extends JPanel {

    public Sidebar(String[] menuItems, String logoPath) {
        setPreferredSize(new Dimension(200, 700));
        setBackground(new Color(255, 102, 102));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JLabel logo = new JLabel(new ImageIcon(logoPath));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logo);


        JLabel title = new JLabel("Super Admin", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(title);

        for (String item : menuItems) {
            JButton menuButton = ButtonFactory.createStyledButton(item);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(menuButton);
        }
    }
}
