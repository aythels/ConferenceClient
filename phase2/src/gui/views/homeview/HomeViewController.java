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
        //FXMLLoader fxmlLoader = new FXMLLoader();
        //Pane pane = fxmlLoader.load(getClass().getResource("settingsview/settingsview.fxml").openStream());
        //SettingsViewController paneController = (SettingsViewController) fxmlLoader.getController();
        //paneController.setPresenter(presenter);

        //Scene scene = new Scene(pane);
        //Stage stage = new Stage();
        //stage.setScene(scene);
        //stage.setTitle("Settings");
        //stage.show();
    }

    public void eventsButtonOnClick(ActionEvent event) throws IOException {
        //pageIndex.setPage("eventsview");
    }

    public void messengerButtonOnClick(ActionEvent event) throws IOException {
        //pageIndex.setPage("messengerview");
    }

}
