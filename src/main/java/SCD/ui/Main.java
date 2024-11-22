package SCD.ui;
//package SCD.ui;
//
//public class Main {
//    public static void main(String[] args)
//    {
//        SplashScreen splashScreen = new SplashScreen();
//        splashScreen.showSplashScreen();
//    }
//}


import SCD.ui.SplashScreen;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
        });
    }
}