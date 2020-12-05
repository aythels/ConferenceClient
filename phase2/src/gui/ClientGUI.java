package gui;

import api.API;
import gui.helpers.ClientData;
import gui.helpers.PageIndex;
import gui.views.ControllerFactory;
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
        ControllerFactory pageControllerFactory = new ControllerFactory(pageIndex, clientData, api);

        pageIndex.addPage("loginview", createScene("views/loginview/loginview.fxml", pageControllerFactory));
        pageIndex.addPage("homeview", createScene("views/homeview/homeview.fxml", pageControllerFactory));

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
