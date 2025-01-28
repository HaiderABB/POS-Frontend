package SCD.controllers.CommonControllers;

import SCD.ui.Common.SplashScreen;

public class SplashScreenController {

  public SplashScreenController() {

    new SplashScreen().setVisible(true);

  }

  public void CloseSplashScreen() {

    // scd- proj initClose the splash screen
    // scd- proj initsplashScreen.dispose();

    // scd- proj initnew MainMenuController();

  }

  public static void main(String[] args) {
    new SplashScreenController();
  }

}
