package gui.views.homeview.settingsview;

import gui.presenters.SettingsPresenter;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsViewController {
    private SettingsPresenter presenter;

    public void setPresenter(SettingsPresenter presenter) {
        this.presenter = presenter;
        update();
    }

    public Label usernameText;
    public Label usertypeText;

    public void update() {
        usernameText.setText(this.presenter.getUserName());
        usertypeText.setText(this.presenter.getUserType());
        pass1.clear();
        pass2.clear();
        passwordError.setVisible(false);
        createError.setVisible(false);
    }

    public TextField display;

    public void saveButtonOnClick() {
        presenter.changeDisplayName(display.getText());
        update();
    }

    public PasswordField pass1;
    public PasswordField pass2;
    public Label passwordError;

    public void save2ButtonOnClick() {
        if (!pass2.getText().equals(pass1.getText())) {
            passwordError.setVisible(true);
            return;
        }

        presenter.changePassword(pass1.getText());
        createError.setVisible(false);
        update();
    }

    public ChoiceBox createType;
    public TextField createName;
    public PasswordField createPass1;
    public PasswordField createPass2;
    public Label createError;

    public void createAccountButtonOnClick() {
        if (!createPass2.getText().equals(createPass1.getText())) {
            createError.setVisible(true);
            return;
        }

        boolean success = presenter.createAccount(createName.getText(), createPass1.getText(), (String) createType.getSelectionModel().getSelectedItem());

        System.out.println(success);
        if (success) {
            createName.clear();
            createPass1.clear();
            createPass2.clear();
            createError.setVisible(false);
            update();
        } else {
            createError.setVisible(true);
        }
    }

    public void closeButtonOnClick() {
        Stage stage = (Stage) createType.getScene().getWindow();
        stage.close();
    }

    public void saveAllButtonOnClick() {
        saveButtonOnClick();
        save2ButtonOnClick();
    }
}
