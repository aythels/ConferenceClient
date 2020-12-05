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
                "ATTENDEE", "Bob1", "attendee1", "pass");

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "SPEAKER", "Joe1", "speaker1", "pass");

        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "ORGANIZER", "Lee1", "organizer1", "pass");


        ClientGUI client = new ClientGUI(primaryStage, api);
        //ClientGUI client2 = new ClientGUI(new Stage(), api);
    }
}

