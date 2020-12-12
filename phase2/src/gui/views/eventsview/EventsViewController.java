
/*
package gui.views.eventsview;

import gui.presenters.EventsPresenter;
import gui.views.eventsview.eventnode.EventNodeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EventsViewController implements PageUpdateEvent {
    private final PageIndex pageIndex;
    private final EventsPresenter presenter;

    public EventsViewController(PageIndex pageIndex, EventsPresenter presenter) {
        this.pageIndex = pageIndex;
        this.presenter = presenter;
        pageIndex.addPageUpdateObserver(this);
    }

    public Button returnButton;
    public Button downloadButton;
    public TextField searchInput;
    public ChoiceBox filterChoice;
    public Pane eventListPane;

    public void returnButtonOnClick() {
        pageIndex.setPage("homeview");
    }

    public void downloadButtonOnClick() {
        System.out.println("NOT IMPLEMENTED");
    }

    public void searchInputOnChange() {
        filterChoiceOnChange();
    }

    public void filterChoiceOnChange() {
        int index = filterChoice.getSelectionModel().getSelectedIndex();

        if (index == 0) processEvents(presenter.getEventDetailsByDate(searchInput.getText()));
        else if (index == 1) processEvents(presenter.getEventDetailsByName(searchInput.getText()));
        else if (index == 2) processEvents(presenter.getEventDetailsByRegistered(searchInput.getText()));

    }

    public void loadEvent(String name, String speakers, String time) throws IOException {
        //load the new event pane
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("eventnode/eventnode.fxml").openStream());
        EventNodeController paneController = (EventNodeController) fxmlLoader.getController();

        //set the pane attributes
        paneController.setEventName(name);
        paneController.setEventSpeaker(speakers);
        paneController.setEventDate(new SimpleDateFormat("MMMMM d yyyy").format(new Date(Long.valueOf(time) * 1000)));
        paneController.setTimer(new SimpleDateFormat("HH:mm z").format(new Date(Long.valueOf(time) * 1000)));

        //add the new event pane
        eventListPane.getChildren().add(pane);
    }

    public void processEvents(ArrayList<HashMap<String, String>> allEvents) {
        eventListPane.getChildren().clear();

        for (HashMap<String, String> event : allEvents) {
            try {
                loadEvent(
                        event.get("eventName"),
                        event.get("speakerName"),
                        event.get("eventTime"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        processEvents(presenter.getEventDetailsByDate(""));
        searchInput.clear();
        filterChoice.getSelectionModel().selectFirst();
    }

}
*/