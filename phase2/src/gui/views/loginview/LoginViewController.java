package gui.views.loginview;

import api.API;
import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import gui.helpers.PageUpdateEvent;
import gui.presenters.LoginPresenter;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable, PageUpdateEvent {
    private final LoginPresenter presenter;
    private final PageIndex pageIndex;

    public LoginViewController(PageIndex pageIndex, LoginPresenter presenter) {
        this.presenter = presenter;
        this.pageIndex = pageIndex;
        pageIndex.addPageUpdateObserver(this);
    }

    public TextField usernameInput;
    public TextField passwordInput;
    public Label warningText;

    public void loginButtonOnClick() {
        if (this.presenter.login(usernameInput.getText(), passwordInput.getText())) {
            pageIndex.setPage("homeview");
        } else {
            warningText.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameInput.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    loginButtonOnClick();
                }
            }
        });

        passwordInput.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    loginButtonOnClick();
                }
            }
        });
    }

    @Override
    public void update() {
        usernameInput.clear();
        passwordInput.clear();
        warningText.setVisible(false);
    }
}

