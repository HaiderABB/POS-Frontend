package SCD.ui.Common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class SplashScreen extends JFrame implements Props {
    private JProgressBar progressBar;
    private JLabel logo;

    public SplashScreen() {
        // scd- proj initFrame settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);

        // scd- proj initSet custom cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.getImage("C:\\Users\\AMMAR\\Desktop\\Parhai\\SCD\\POS-Frontend\\cursor.png");
        Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "Custom Cursor");
        setCursor(customCursor);

        // scd- proj initSplash Panel
        JPanel splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // scd- proj initGradient background
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = Props.bg;
                Color endColor = new Color(255, 153, 153);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        splashPanel.setLayout(null);

        // scd- proj initLogo
        logo = new JLabel(new ImageIcon("logo5.png"));
        logo.setBounds(350, 200, 100, 100);
        splashPanel.add(logo);

        // scd- proj initWelcome Text
        JLabel welcomeText = new JLabel("WELCOME TO POS", JLabel.CENTER);
        welcomeText.setBounds(250, 320, 300, 50);
        welcomeText.setForeground(Props.fg);
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24));
        splashPanel.add(welcomeText);

        // scd- proj initProgress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(250, 400, 300, 20);
        progressBar.setForeground(Props.bg);
        progressBar.setBackground(Props.fg);
        progressBar.setStringPainted(true);
        splashPanel.add(progressBar);

        // scd- proj initAdd Panel
        add(splashPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);

        // scd- proj initStart animations and progress
        startAnimation();
    }

    private void startAnimation() {
        // scd- proj initLogo animation (move logo upwards)
        Timer logoTimer = new Timer(10, new AbstractAction() {
            int y = 200; // scd- proj initInitial y-position of the logo

            @Override
            public void actionPerformed(ActionEvent e) {
                y--;
                logo.setBounds(350, y, 100, 100);
                if (y <= 150) { // scd- proj initStop animation when logo reaches desired position
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        logoTimer.start();

        // scd- proj initProgress bar animation
        Timer progressTimer = new Timer(30, new AbstractAction() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress++;
                progressBar.setValue(progress);
                if (progress >= 100) {
                    ((Timer) e.getSource()).stop();
                    dispose();
                    new MainMenu().setVisible(true);
                }
            }
        });
        progressTimer.start();
    }

}
