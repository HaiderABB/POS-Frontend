package SCD.ui.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavBar extends JPanel implements Props {
    private JLabel titleLabel;

    public NavBar() {
        setPreferredSize(new Dimension(1000, 50));
        setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout());

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.CENTER);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}
