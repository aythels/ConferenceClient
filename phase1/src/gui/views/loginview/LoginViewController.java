package gui.views.loginview;

import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import gui.helpers.PageInitializeEvent;
import gui.helpers.PageUpdateEvent;
import gui.views.PageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.InputMethodTextRun;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController extends PageController {
    public TextField usernameInput;
    public TextField passInput;

    public LoginViewController(PageIndex pageIndex, ClientData clientData) {
        super(pageIndex, clientData);
    }

    public void loginButtonOnClick(ActionEvent event) throws IOException {
        System.out.println(usernameInput.getText());
        System.out.println(passInput.getText());

        usernameInput.clear();
        passInput.clear();

        pageIndex.setPage("homeview");
    }

}
