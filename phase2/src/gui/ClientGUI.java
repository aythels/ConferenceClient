package gui;

import api.API;
import gui.helpers.ClientData;
import gui.helpers.ControllerFactory;
import gui.helpers.PageManager;
import gui.helpers.Presenters;
import gui.presenters.EventsPresenter;
import gui.presenters.LoginPresenter;
import gui.presenters.MessagePresenter;
import gui.presenters.SettingsPresenter;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientGUI {

    public ClientGUI (Stage stage, API api) throws IOException {
        ClientData clientData = new ClientData();
        Presenters presenters = new Presenters();

        presenters.eventsPresenter = new EventsPresenter(api, clientData);
        presenters.loginPresenter = new LoginPresenter(api, clientData);
        presenters.messagePresenter = new MessagePresenter(api, clientData);
        presenters.settingsPresenter = new SettingsPresenter(api, clientData);

        Scene scene = PageManager.createScene(
                getClass().getResource("views/loginview/loginview.fxml"),
                new ControllerFactory(stage, presenters)
            );
        PageManager.setScene(stage, scene);

    }
}

