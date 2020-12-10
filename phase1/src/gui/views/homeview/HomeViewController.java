package gui.views.homeview;

import gui.helpers.PageIndex;
import gui.presenters.SettingsPresenter;
import gui.views.eventsview.eventnode.EventNodeController;
import gui.views.homeview.settingsview.SettingsViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeViewController {
    private final PageIndex pageIndex;
    private final SettingsPresenter presenter;

    public HomeViewController(PageIndex pageIndex, SettingsPresenter presenter) {
        this.pageIndex = pageIndex;
        this.presenter = presenter;
    }

    public void logoutButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

    public void settingsButtonOnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("settingsview/settingsview.fxml").openStream());
        SettingsViewController paneController = (SettingsViewController) fxmlLoader.getController();
        paneController.setPresenter(presenter);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }

    public void eventsButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("eventsview");
    }

    public void messengerButtonOnClick(ActionEvent event) throws IOException {
        pageIndex.setPage("loginview");
    }

}
