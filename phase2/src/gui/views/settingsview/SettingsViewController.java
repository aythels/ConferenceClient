package gui.views.settingsview;

import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import gui.presenters.SettingsPresenter;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsViewController implements Initializable {
    private final Stage stage;
    private final Presenters presenters;

    public SettingsViewController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    //on page load in
    public Label usernameText;
    public Label usertypeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameText.setText(presenters.settingsPresenter.getUserName());
        usertypeText.setText(presenters.settingsPresenter.getUserType());
    }

    //change display name
    public TextField displayText;

    public void saveButtonOnClick() {
        presenters.settingsPresenter.changeDisplayName(displayText.getText());
    }

    //change password module
    public PasswordField pass1;
    public PasswordField pass2;
    public Label passwordError;

    public void save2ButtonOnClick() {
        if (!pass1.getText().equals(pass2.getText())) {
            passwordError.setVisible(true);
            return;
        }

        presenters.settingsPresenter.changePassword(pass1.getText());

        pass1.clear();
        pass2.clear();
        passwordError.setVisible(false);
    }

    //create account
    public ChoiceBox createTypeChoice;
    public TextField createNameText;
    public PasswordField createPass1Text;
    public PasswordField createPass2Text;
    public Label createErrorText;

    public void createAccountButtonOnClick() {
        if (!createPass2Text.getText().equals(createPass1Text.getText())) {
            createErrorText.setVisible(true);
            return;
        }

        boolean success = presenters.settingsPresenter.createAccount(
                createNameText.getText(),
                createPass1Text.getText(),
                (String) createTypeChoice.getSelectionModel().getSelectedItem());

        if (success) {
            createNameText.clear();
            createPass1Text.clear();
            createPass2Text.clear();
            createErrorText.setVisible(false);
        } else {
            createErrorText.setVisible(true);
        }
    }

    //buttons at bottom of page

    public void closeButtonOnClick() {
        URL url = getClass().getResource("./../homeview/homeview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public void saveAllButtonOnClick() {
        saveButtonOnClick();
        save2ButtonOnClick();
    }

}
