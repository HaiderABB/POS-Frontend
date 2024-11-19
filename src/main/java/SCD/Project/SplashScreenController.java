//package SCD.Project;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class SplashScreenController {
//    private SplashScreenView splashScreen;
//
//    public SplashScreenController(SplashScreenView splashScreen) {
//        this.splashScreen = splashScreen;
//    }
//
//    public void showSplashScreen() {
//        splashScreen.setVisible(true);
//
//        Timer fadeOutTimer = new Timer(50, new ActionListener() {
//            private float opacity = 1.0f;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                opacity -= 0.05f;
//                if (opacity <= 0.0f) {
//                    splashScreen.setOpacity(0.0f);
//                    ((Timer) e.getSource()).stop();
//                    splashScreen.dispose();
//                    showLoginPage();
//                } else {
//                    splashScreen.setOpacity(opacity);
//                }
//            }
//        });
//
//        new Timer(3000, (ActionEvent e) -> fadeOutTimer.start()).setRepeats(false).start();
//    }
//
//    private void showLoginPage() {
//        UserModel model = new UserModel("", "");
//        LoginView view = new LoginView();
//        LoginController controller = new LoginController(model, view);
//        view.setVisible(true);
//    }
//}