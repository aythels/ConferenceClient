package ui.views;

import api.controllers.logincontrollers.PublicLoginController;
import api.controllers.usercontrollers.PublicUserController;
import ui.UIContext;

public class LoginView extends View {
    private String username, password, error;
    public LoginView(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();
        if (error != null) {
            sb.append("Error: ").append(error).append("\n");
        }
        if (username == null) {
            sb.append("Username: ");
        } else {
            sb.append("Password: ");
        }
        return sb.toString();
    }

    @Override
    public void handleInput(String input) {
        error = null;
        if (username == null) {
            username = input;
        } else {
            password = input;
            PublicLoginController loginController = (PublicLoginController) this.context.server.getAPI().getLoginAPI();
            String accessCode = loginController.login(username, password);
            if (accessCode != null) {
                this.context.putState("accessCode", accessCode);
                PublicUserController userController = (PublicUserController) this.context.server.getAPI().getUserAPI();
                String userRole = userController.getUserType(username);

                switch (userRole) {
                    case "ATTENDEE":
                        this.context.navigate("home_attendee");
                        break;
                    case "ORGANIZER":
                        this.context.navigate("home_organizer");
                        break;
                    case "SPEAKER":
                        this.context.navigate("home_speaker");
                        break;
                    default:
                        error = "Invalid user!";
                }
            } else {
                error = "Invalid credentials!";
            }
        }
    }

    @Override
    public String getRoute() {
        return "login";
    }
}
