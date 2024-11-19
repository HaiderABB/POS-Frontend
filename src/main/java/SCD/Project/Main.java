package SCD.Project;

public class Main {
    public static void main(String[] args) {
        UserModel model = new UserModel("", "");
        LoginView view = new LoginView();
        LoginController controller = new LoginController(model, view);

        view.setVisible(true);
    }
}
