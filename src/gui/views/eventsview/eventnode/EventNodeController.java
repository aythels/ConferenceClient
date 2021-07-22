package gui.views.eventsview.eventnode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EventNodeController {

    public Button detailsButton;
    public Button registerButton;

    public void setRegisterButtonState(int state) {
        switch(state) {
            case 0:
                //the client can register for this event,
                registerButton.setText("REGISTER");
                registerButton.setStyle("-fx-background-color: #e3e3e3");
                registerButton.setDisable(false);
                break;
            case 1:
                //the client is already registered for this event,
                registerButton.setText("REGISTERED");
                registerButton.setStyle("-fx-background-color: #e3e3e3");
                registerButton.setDisable(false);
                break;
            case 2:
                //the client is the speaker for this event, button disabled
                registerButton.setText("SPEAKER");
                registerButton.setStyle("-fx-background-color: #34eb74");
                registerButton.setDisable(true);
                break;
            case 3:
                //the client is not permitted to register for this event, button disabled
                registerButton.setText("VIP");
                registerButton.setStyle("-fx-background-color: #b03fa7");
                registerButton.setDisable(true);
                break;
        }
    }

    public void onDetailsButtonClick() {
    }

    public void onRegisterButtonClick() {
    }

    public Label nameText;
    public Label speakerText;
    public Label dateText;
    public Label timerText;

    public void setEventName(String text){
        nameText.setText(text);
    }

    public void setEventSpeaker(String text){
        speakerText.setText(text);
    }

    public void setEventDate(String text){
        dateText.setText(text);
    }

    public void setTimer(String text){
        timerText.setText(text);
    }

}
