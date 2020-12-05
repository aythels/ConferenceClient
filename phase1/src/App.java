import adapters.Client;
import adapters.Server;
import api.controllers.usercontrollers.PublicUserController;

public class App {
    public static void main(String[] args) {
        Server server = new Server();



        PublicUserController abc = (PublicUserController) server.getAPI().getUserAPI();

        abc.createAnyUserTEMPORARY("ATTENDEE", "Bob1", "attendee1", "pass");
        abc.createAnyUserTEMPORARY("ATTENDEE", "Bob2", "attendee2", "pass");
        abc.createAnyUserTEMPORARY("ATTENDEE", "Bob3", "attendee3", "pass");

        abc.createAnyUserTEMPORARY("SPEAKER", "Lee1", "speaker1", "pass");
        abc.createAnyUserTEMPORARY("SPEAKER", "Lee2", "speaker2", "pass");

        abc.createAnyUserTEMPORARY("ORGANIZER", "Joe1", "organizer1", "pass");

        Client client = new Client(server);

    }
}

