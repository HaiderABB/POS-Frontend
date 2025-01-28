package SCD.ui;
//package SCD.ui;

//
//public class Main {
// scd- proj init   public static void main(String[] args)
// scd- proj init   {
// scd- proj init       SplashScreen splashScreen = new SplashScreen();
// scd- proj init       splashScreen.showSplashScreen();
// scd- proj init   }
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