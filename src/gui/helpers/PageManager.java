package gui.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

public final class PageManager {

    public static void setWindowPage(Stage stage, URL filePath, Callback<Class<?>, Object> controllerFactory) {
        Scene scene = createScene(
                filePath,
                controllerFactory);

        setScene(stage, scene);
    }

    public static Scene createScene(URL filePath) {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(filePath);

        try {
            return new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Scene createScene(URL filePath, Callback<Class<?>, Object> controllerFactory) {
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(controllerFactory);
        loader.setLocation(filePath);

        try {
            return new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setScene (Stage stage, Scene scene) {
        stage.setTitle("Phase 2");
        stage.sizeToScene();

        stage.setScene(scene);
        stage.show();

        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

}
