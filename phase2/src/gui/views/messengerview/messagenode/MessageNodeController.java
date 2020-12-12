package gui.views.messengerview.messagenode;

import javafx.scene.control.Label;

public class MessageNodeController {

    public Label displayNameText;
    public Label messageText;

    public void setName(String text){
        displayNameText.setText(text);
    }

    public void setMessage(String text){
        messageText.setText(text);
    }
}
