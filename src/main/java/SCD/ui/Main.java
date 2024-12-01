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


import javax.swing.*;

import SCD.ui.Common.SplashScreen;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
        });
    }
}