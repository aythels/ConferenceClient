package gui.views.messengerview.convonode;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ConvoNodeController implements Initializable {

    public Label nameText;
    public Label firstMessageText;
    public HBox backgroundPane;
    public HBox[] allConvoPanes;

    public void paneOnClick() {
        //for (HBox pane : allConvoPanes) {
        //    nameText.setTextFill(Color.BLACK);
        //    backgroundPane.setStyle("-fx-background-color: #dbdbdb");
        //}

        //setActive(true);
    }

    public void setActive(Boolean b) {
        if (b) {
            nameText.setTextFill(Color.WHITE);
            firstMessageText.setTextFill(Color.WHITE);
            backgroundPane.setStyle("-fx-background-color: #19b2ff; -fx-background-radius: 10;");
        } else {
            nameText.setTextFill(Color.BLACK);
            firstMessageText.setTextFill(Color.BLACK);
            backgroundPane.setStyle("-fx-background-color: #dbdbdb; -fx-background-radius: 10;");
        }
    }

    public void setName(String text){
        nameText.setText(text);
    }

    public void setLastMessageText(String text){
        firstMessageText.setText(text);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setActive(true);
    }

}
