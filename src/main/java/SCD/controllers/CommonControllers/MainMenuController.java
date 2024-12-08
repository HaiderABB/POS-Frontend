package SCD.controllers.CommonControllers;


import SCD.ui.Common.LoginPage;
import SCD.ui.Common.MainMenu;


import javax.swing.*;

public class MainMenuController {

    public static void handleRoleButtonClick(JFrame mainMenu, String prefix, String role) {
        LoginPage loginPage = new LoginPage(prefix);
        loginPage.setVisible(true);

        mainMenu.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
