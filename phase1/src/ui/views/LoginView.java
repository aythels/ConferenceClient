package ui.views;

import ui.UIContext;

public class LoginView extends View {
    private String username, password;
    public LoginView(UIContext context) {
        super(context);
    }

    @Override
    public String render() {
        if (username == null)
            return "Please enter your username: ";
        return "Please enter your password: ";
    }

    @Override
    public void handleInput(String input) {
        if (username == null) {
            username = input;
        } else {
            password = input;
            // do something with credentials here
            this.context.navigate("home");
        }
    }

    @Override
    public String getRoute() {
        return "login";
    }
}
