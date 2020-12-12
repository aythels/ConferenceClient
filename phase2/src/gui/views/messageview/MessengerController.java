/*
package gui.views.messageview;

import gui.helpers.PageIndex;
import gui.helpers.PageUpdateEvent;
import gui.presenters.MessagePresenter;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MessengerController implements PageUpdateEvent {
    private final PageIndex pageIndex;
    private final MessagePresenter presenter;

    public MessengerController(PageIndex pageIndex, MessagePresenter presenter) {
        this.pageIndex = pageIndex;
        this.presenter = presenter;
        pageIndex.addPageUpdateObserver(this);
    }

    public Button returnButton;
    public ChoiceBox filterChoice;
    public Button newMessageButton;
    public TextField messageInput;
    public Button sendButton;

    public void returnButtonOnClick() {
        pageIndex.setPage("homeview");
    }

    public void filterChoiceOnChange() {

    }
    public void newMessageButtonOnClick(){
        System.out.println("NOT IMPLEMENTED");
    }

    public void sendButtonOnClick() {
        sendButton.setText("Sent");
    }

    @Override
    public void update() {
        messageInput.clear();
        filterChoice.getSelectionModel().selectFirst();

    }

}
*/