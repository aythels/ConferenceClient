package gui.views.eventsview.eventnode;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EventNodeController {

    public Button detailsButton;
    public Button registerButton;

    public void setRegisterButtonState(int state) {
        switch(state) {
            case 0:
                registerButton.setText("REGISTER");
                registerButton.setStyle("-fx-background-color: #e3e3e3");
                registerButton.setDisable(false);
                break;
            case 1:
                registerButton.setText("REGISTERED");
                registerButton.setStyle("-fx-background-color: #e3e3e3");
                registerButton.setDisable(true);
                break;
            case 2:
                registerButton.setText("DISABLED");
                registerButton.setStyle("-fx-background-color: #f5b342");
                registerButton.setDisable(true);
                break;
            case 3:
                registerButton.setText("SPEAKER");
                registerButton.setStyle("-fx-background-color: #34eb74");
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
