package gui;

import api.API;
import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import gui.presenters.EventsPresenter;
import gui.presenters.LoginPresenter;
import gui.views.ControllerFactory;
import gui.views.loginview.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class ClientGUI {

    public ClientGUI (Stage stage, API api) throws IOException {
        PageIndex pageIndex = new PageIndex(stage);
        ClientData clientData = new ClientData();

        pageIndex.addPage("loginview", createScene("views/loginview/loginview.fxml", new ControllerFactory(pageIndex, new LoginPresenter(api, clientData))));
        pageIndex.addPage("homeview", createScene("views/homeview/homeview.fxml", new ControllerFactory(pageIndex)));
        pageIndex.addPage("eventsview", createScene("views/eventsview/eventsview.fxml", new ControllerFactory(pageIndex, new EventsPresenter(api, clientData))));

        pageIndex.setPage("loginview");
    }


    private Scene createScene(String filePath) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource(filePath));

        return new Scene(loader.load());
    }

    private Scene createScene(String filePath, Callback<Class<?>, Object> controllerFactory) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(controllerFactory);
        loader.setLocation(getClass().getResource(filePath));

        return new Scene(loader.load());
    }

}
