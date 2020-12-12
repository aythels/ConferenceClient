package gui.views.messengerview;

import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import gui.views.messengerview.convonode.ConvoNodeController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessengerController implements Initializable {
    private final Stage stage;
    private final Presenters presenters;

    public MessengerController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    public void returnButtonOnClick() {
        URL url = getClass().getResource("./../homeview/homeview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public void refreshButtonOnClick() throws IOException {
        loadConvos();
    }

    public void newMessageButtonOnClick() {

    }

    /*
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

    public VBox messageListPane;

*/

    public VBox convoListPane;
    public void loadConvos() throws IOException {
        convoListPane.getChildren().clear();

        String[] convosUsers = presenters.messagePresenter.getAllConvoUsers();

        for (String user : convosUsers) {
            FXMLLoader fxmlLoader = new FXMLLoader();

            Pane pane = fxmlLoader.load(getClass().getResource("convonode/convonode.fxml").openStream());
            ConvoNodeController paneController = (ConvoNodeController) fxmlLoader.getController();

            //String[] convo = presenters.messagePresenter.getAllConvoMessages(user);

            convoListPane.getChildren().add(pane);

            String displayName = presenters.messagePresenter.getUserDisplayName(user);
            String[] messageArray = presenters.messagePresenter.getAllConvoMessages(user);
            String lastMessage = messageArray[messageArray.length-1];

            paneController.setName(displayName);
            paneController.setLastMessageText(lastMessage.substring(lastMessage.lastIndexOf(":") + 1));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadConvos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
