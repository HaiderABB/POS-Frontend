package SCD.controllers.CommonControllers;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import SCD.ui.Common.MainMenu;

public class MainMenuController {

    public MainMenuController() {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }

    public static void handleRoleButtonClick(JFrame mainMenu, String prefix, String role) {
        // LoginPage loginPage = new LoginPage(prefix);
        // loginPage.setVisible(true);

        // new LoginScreenController("role")

        mainMenu.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
