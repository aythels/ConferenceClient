package gui.views.loginview;

import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginViewController {
    private final ClientData clientData;
    private final PageIndex pageIndex;

    public TextField usernameInput;
    public TextField passInput;

    public LoginViewController(PageIndex pageIndex, ClientData clientData) {
        this.clientData = clientData;
        this.pageIndex = pageIndex;
    }

    public void loginButtonOnClick(ActionEvent event) throws IOException {
        System.out.println(usernameInput.getText());
        System.out.println(passInput.getText());

        usernameInput.clear();
        passInput.clear();

        pageIndex.setPage("homeview");
    }

}
