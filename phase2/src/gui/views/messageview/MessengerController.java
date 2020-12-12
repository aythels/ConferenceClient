package gui.views.messageview;

import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MessengerController implements Initializable {
    private final Stage stage;
    private final Presenters presenters;

    public MessengerController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    public Button newMessageButton;

    public void newMessageButtonOnClick(){
        System.out.println("NOT IMPLEMENTED");
    }

    public Button sendButton;
    public TextField messageInput;
    public void sendButtonOnClick() {
        sendButton.setText("Sent");
    }

    public Button returnButton;
    public void returnButtonOnClick() {
        URL url = getClass().getResource("./../homeview/homeview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public VBox convoListPane;
    public VBox messageListPane;

    public void loadConvos() {
        //convoListPane.getChildren().add(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadConvos();
    }
}
