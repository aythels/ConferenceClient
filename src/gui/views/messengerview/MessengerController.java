package gui.views.messengerview;

import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import gui.views.messengerview.convonode.ConvoNodeController;
import gui.views.messengerview.messagenode.MessageNodeController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessengerController implements Initializable {
    private final Stage stage;
    private final Presenters presenters;

    public MessengerController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    private ArrayList<ConvoNodeController> convoNodeControllers;
    private ConvoNodeController activeController;

    public void returnButtonOnClick() {
        URL url = getClass().getResource("./../homeview/homeview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public void refreshButtonOnClick() {
        try {
            loadConvo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageListPane.getChildren().clear();
        messageInput.setDisable(true);
        sendButton.setDisable(true);
    }

    public void newMessageButtonOnClick() {

    }

    public TextArea messageInput;
    public Button sendButton;
    public void sendMessageButtonOnClick() {
        if (messageInput.getText().length() == 0) return;

        presenters.messagePresenter.sendMessage(activeController.username, messageInput.getText());
        try {
            loadMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageInput.clear();
    }

    public VBox convoListPane;
    public void loadConvo() throws IOException {
        convoListPane.getChildren().clear();

        String[] convosUsers = presenters.messagePresenter.getAllConvoUsers();
        convoNodeControllers =  new ArrayList<ConvoNodeController>();

        for (String user : convosUsers) {
            FXMLLoader fxmlLoader = new FXMLLoader();

            Pane pane = fxmlLoader.load(getClass().getResource("convonode/convonode.fxml").openStream());
            ConvoNodeController paneController = (ConvoNodeController) fxmlLoader.getController();
            paneController.master = this;
            paneController.username = user;

            convoListPane.getChildren().add(pane);

            String displayName = presenters.messagePresenter.getUserDisplayName(user);
            String[] messageArray = presenters.messagePresenter.getAllConvoMessages(user);
            String lastMessage = messageArray[messageArray.length-1];

            paneController.setName(displayName);
            paneController.setLastMessageText(lastMessage.substring(lastMessage.lastIndexOf(":") + 1));

            convoNodeControllers.add(paneController);
        }
    }

    public VBox messageListPane;
    public void loadMessages() throws IOException {
        messageListPane.getChildren().clear();
        String[] convo = presenters.messagePresenter.getAllConvoMessages(activeController.username);

        for (String message : convo) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane pane = fxmlLoader.load(getClass().getResource("messagenode/messagenode.fxml").openStream());
            MessageNodeController paneController = (MessageNodeController) fxmlLoader.getController();

            String u = message.split("\\:")[0];
            String m = message.substring(message.lastIndexOf(":") + 1);

            paneController.setName(presenters.messagePresenter.getUserDisplayName(u));
            paneController.setMessage(m);

            messageListPane.getChildren().add(pane);
        }
    }

    public void onConvoFocus(String username, ConvoNodeController c) {
        for (ConvoNodeController controller: convoNodeControllers) {
            controller.setActive(false);
        }
        activeController = c;


        try {
            loadMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.setActive(true);
        messageInput.setDisable(false);
        sendButton.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshButtonOnClick();
    }
}
