import api.controllers.Controller;
import api.controllers.eventcontrollers.OrganizerEventController;
import api.controllers.logincontrollers.PublicLoginController;
import api.controllers.usercontrollers.PublicUserController;

public class UserTest {
    /*********************************************************************************************************/
    //THIS CLASS IS ONLY FOR TESTING THE BACKEND!!!
    //RUN MAIN IN APP TO START THE ACTUAL PROGRAM!
    /*********************************************************************************************************/

    public static void main(String[] args) {
        adapters.Server server = new adapters.Server();
        //adapters.Client client = new adapters.Client(server.getAPI());

        //run tests here
        System.out.println("Hello, World");

        PublicUserController abc = (PublicUserController) server.getAPI().getUserAPI();
        abc.createAnyUserTEMPORARY("ORGANIZER", "Joe", "TestLogin123", "testpassword123");

        PublicLoginController cba = (PublicLoginController) server.getAPI().getLoginAPI();

        String accessCode = cba.login("TestLogin123", "testpassword123");

        System.out.println(accessCode);

        OrganizerEventController test = (OrganizerEventController) server.getAPI().getEventAPI(accessCode);
        System.out.println(test);

    }

}
