package gui.views.loginview;

import api.API;
import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginViewController {
    private final ClientData clientData;
    private final PageIndex pageIndex;
    private final API api;

    public TextField usernameInput;
    public TextField passInput;
    public Label loginText;

    public LoginViewController(PageIndex pageIndex, ClientData clientData, API api) {
        this.clientData = clientData;
        this.pageIndex = pageIndex;
        this.api = api;
    }

    public void reset() {
        usernameInput.clear();
        passInput.clear();
        loginText.setVisible(false);
    }

    public void loginButtonOnClick(ActionEvent event) throws IOException {
        String accessCode = api.call("login_controller", null, "login",
                usernameInput.getText(), passInput.getText());

        if (accessCode != "null") {
            reset();
            pageIndex.setPage("homeview");
        } else
            loginText.setVisible(true);

    }

}
