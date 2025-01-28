package SCD.ui.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonFactory {

    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(230, 40));
        button.setFont(new Font("", Font.BOLD, 15));
        button.setBackground(Props.bg);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(Props.fg);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Props.btnBg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Props.bg);
            }
        });

        return button;
    }

    public static JButton createMainMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Props.bg);
        button.setForeground(Props.fg);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Props.btnBg);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(Props.bg);
            }
        });

        return button;
    }

    public static JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Props.bg);
        button.setForeground(Props.fg);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));

        ImageIcon icon = new ImageIcon(iconPath);
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Props.btnBg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Props.bg);
            }
        });

        return button;
    }
}
