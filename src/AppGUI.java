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
                "ATTENDEE", "Attendee Bob", "a", "a");

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "ORGANIZER", "Organizer Joe", "b", "b");

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "SPEAKER", "Speaker Lee", "c", "c");

        String accessCode = api.call("login_controller", null, "login",
                "b", "b");

        api.call("event_controller", accessCode, "createEvent",
                "A Test Event",
                1, (int) (System.currentTimeMillis() / 1000) + 1000, 100);

        api.call("event_controller", accessCode, "createEvent",
                "B Test Event",
                1, (int) (System.currentTimeMillis() / 1000), 100);

        api.call("event_controller", accessCode, "setEventSpeaker",
                1, "c");

        api.call("event_controller", accessCode, "setEventSpeaker",
                1, "c");

        api.call("event_controller", accessCode, "registerInEvent",
                accessCode, 1);

        api.call("messenger_controller", accessCode,
                "messageUserByID",
                accessCode,
                "c",
                "This is a test message");

        api.call("messenger_controller", accessCode,
                "messageUserByID",
                accessCode,
                "a",
                "This is another test Message");

        ClientGUI client = new ClientGUI(primaryStage, api);
        ClientGUI client2 = new ClientGUI(new Stage(), api);

    }
}

