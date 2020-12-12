import adapters.Server;
import api.API;
import gui.ClientGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Server server = new Server();
        API api = server.getAPI();

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "ORGANIZER", "Lee1", "u", "p");

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "SPEAKER", "Lee2", "a", "p");

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "SPEAKER", "Lee3", "b", "p");

        String accessCode = api.call("login_controller", null, "login",
                "u", "p");

        api.call("event_controller", accessCode, "createEvent",
                "Test Event", 1, (int) (System.currentTimeMillis() / 1000) + 1000, 100);

        api.call("event_controller", accessCode, "createEvent",
                "zvent Name", 1, (int) (System.currentTimeMillis() / 1000), 100);

        api.call("event_controller", accessCode, "setEventSpeaker",
                1, "a");

        api.call("event_controller", accessCode, "setEventSpeaker",
                1, "b");

        api.call("event_controller", accessCode, "registerInEvent",
                accessCode, 1);


        ClientGUI client = new ClientGUI(primaryStage, api);
        //ClientGUI client2 = new ClientGUI(new Stage(), api);

    }
}

