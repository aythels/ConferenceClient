import adapters.Client;
import adapters.Server;
import api.controllers.logincontrollers.PublicLoginController;
import api.controllers.usercontrollers.PublicUserController;

public class App {
    public static void main(String[] args) {
        Server server = new Server();

        PublicUserController abc = (PublicUserController) server.getAPI().getUserAPI();

        abc.createAnyUserTEMPORARY("ATTENDEE", "Bob", "user1", "pass1");

        abc.createAnyUserTEMPORARY("SPEAKER", "Lee", "user2", "pass2");

        abc.createAnyUserTEMPORARY("ORGANIZER", "Joe", "user3", "pass3");

        Client client = new Client(server);

    }
}

