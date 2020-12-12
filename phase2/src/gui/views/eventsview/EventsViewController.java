package gui.views.eventsview;

import domain.usecases.GeneratePdf;
import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import gui.presenters.EventsPresenter;
import gui.views.eventsview.eventnode.EventNodeController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EventsViewController implements Initializable {
    private final Stage stage;
    private final Presenters presenters;

    public EventsViewController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    public Button returnButton;
    public void returnButtonOnClick() {
        URL url = getClass().getResource("./../homeview/homeview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public Button downloadButton;
    public void downloadButtonOnClick() {
        ArrayList<HashMap<String, String>> data;
        data = (presenters.eventsPresenter.getEventDetailsByDate(searchInput.getText()));
        GeneratePdf gp = new GeneratePdf(data);
        gp.createPdf();
    }

    public TextField searchInput;
    public void searchInputOnChange() {
        filterChoiceOnChange();
    }

    public ChoiceBox filterChoice;
    public void filterChoiceOnChange() {
        int index = filterChoice.getSelectionModel().getSelectedIndex();

        if (index == 0) processEvents(presenters.eventsPresenter.getEventDetailsByDate(searchInput.getText()));
        else if (index == 1) processEvents(presenters.eventsPresenter.getEventDetailsByName(searchInput.getText()));
        else if (index == 2) processEvents(presenters.eventsPresenter.getEventDetailsByRegistered(searchInput.getText()));

    }

    public Pane eventListPane;
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

    public void loadEvent(String name, String speakers, String time) throws IOException {
        //load the new event pane
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("eventnode/eventnode.fxml").openStream());
        EventNodeController paneController = (EventNodeController) fxmlLoader.getController();

        //set the pane base attributes:
        paneController.setEventName(name);
        paneController.setEventSpeaker(speakers);
        paneController.setEventDate(new SimpleDateFormat("MMMMM d yyyy").format(new Date(Long.valueOf(time) * 1000)));
        paneController.setTimer(new SimpleDateFormat("HH:mm z").format(new Date(Long.valueOf(time) * 1000)));

        //set the pane button attributes:
        //presenters.eventsPresenter
        //paneController.setRegisterButtonState(0);

        //add the new event pane
        eventListPane.getChildren().add(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        processEvents(presenters.eventsPresenter.getEventDetailsByDate(""));
    }
}