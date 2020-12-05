import adapters.Server;
import api.API;
import gui.ClientGUI;
import javafx.application.Application;

public class APITestCases {
    public static void main(String[] args) {
        Server server = new Server();

        API api = server.getAPI();

        //calling api to create user
        api.call("user_controller", null, "createAnyUserTEMPORARY",
                "ATTENDEE", "Bob1", "attendee1", "pass");

        //calling api to login user
        String accessCode = api.call("login_controller", null, "login",
                "attendee1", "pass");

        System.out.println(accessCode);

        //calling api with invalid path
        System.out.println(api.call("////////////////////////////", null, "login",
                "attendee1", "pass"));

        //calling api with invalid accessCode
        System.out.println(api.call("user_controller", "////////////", "createAnyUser",
                "ORGANIZER", "Joe1", "organizer1", "pass"));

        //calling api with wrong permission accessCode
        System.out.println(api.call("user_controller", accessCode, "createAnyUser",
                "ORGANIZER", "Joe1", "organizer1", "pass"));

        //calling api with invalid method name
        System.out.println(api.call("user_controller", null, "////////////////////////",
                "attendee1", "pass"));

        //calling api with invalid arguments
        System.out.println(api.call("login_controller", null, "login",
                11551, 241124));

    }
}


