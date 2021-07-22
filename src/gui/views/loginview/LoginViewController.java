package gui.views.loginview;

import gui.helpers.*;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    private final Stage stage;
    private final Presenters presenters;

    public LoginViewController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    public TextField usernameInput;
    public TextField passwordInput;
    public Label warningText;

    public void loginButtonOnClick() {
        if (presenters.loginPresenter.login(usernameInput.getText(), passwordInput.getText())) {
            URL url = getClass().getResource("./../homeview/homeview.fxml");
            PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));

        } else {
            warningText.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    loginButtonOnClick();
                }
            }
        });
        passwordInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
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

}

