package gui.views.homeview;

import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomeViewController {
    private final Stage stage;
    private final Presenters presenters;

    public HomeViewController(Stage stage, Presenters presenters) {
        this.stage = stage;
        this.presenters = presenters;
    }

    public void logoutButtonOnClick(ActionEvent event) throws IOException {
        URL url = getClass().getResource("./../loginview/loginview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public void settingsButtonOnClick(ActionEvent event) throws IOException {
        URL url = getClass().getResource("./../settingsview/settingsview.fxml");
        PageManager.setWindowPage(stage, url, new ControllerFactory(stage, presenters));
    }

    public void eventsButtonOnClick(ActionEvent event) throws IOException {
        //pageIndex.setPage("eventsview");
    }

    public void messengerButtonOnClick(ActionEvent event) throws IOException {
        //pageIndex.setPage("messengerview");
    }

}
