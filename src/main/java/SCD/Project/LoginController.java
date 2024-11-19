package SCD.Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private UserModel model;
    private LoginView view;

    public LoginController(UserModel model, LoginView view) {
        this.model = model;
        this.view = view;

        // Add Action Listener for Login Button
        this.view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setUsername(view.getUsernameField().getText());
                model.setPassword(new String(view.getPasswordField().getPassword()));

                if (model.validate()) {
                    JOptionPane.showMessageDialog(view, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(view, "Invalid username or password.");
                }
            }
        });
    }
}
