package SCD.controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SCD.ui.LoginPage;
import SCD.ui.UserModel;

public class LoginPageController {
    private UserModel model;
    private LoginPage view;

    public LoginPageController(UserModel model, LoginPage view) {
        this.model = model;
        this.view = view;

        this.view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setUsername(view.getUsername());
                model.setPassword(view.getPassword());
                // Add logic to handle login
                System.out.println("Username: " + model.getUsername());
                System.out.println("Password: " + model.getPassword());
            }
        });
    }

    public static void main(String[] args) {
        UserModel model = new UserModel("", "");
        LoginPage view = new LoginPage();
        LoginPageController controller = new LoginPageController(model, view);
        view.setVisible(true);
    }
}