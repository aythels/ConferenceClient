package api;

import api.controllers.Controller;
import api.controllers.eventcontrollers.*;
import api.helpers.ControllerStorage;
import api.helpers.LoginHelper;
import api.helpers.UserType;
import api.controllers.logincontrollers.PublicLoginController;
import api.controllers.messengercontrollers.AttendeeMessengerController;
import api.controllers.messengercontrollers.SpeakerMessengerController;
import api.controllers.usercontrollers.AttendeeUserController;
import api.controllers.usercontrollers.OrganizerUserController;
import api.controllers.usercontrollers.PublicUserController;
import domain.usecases.EventManager;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

public class InitializeAPI {
    private API api;

    public InitializeAPI(MessageManager messageManager, UserManager userManager, EventManager eventManager){

        //initialize loginHelper
        LoginHelper loginHelper = new LoginHelper();

        //initialize allLoginControllers
        ControllerStorage allLoginControllers = new ControllerStorage();

        Controller publicLogin = new PublicLoginController(userManager, loginHelper);

        allLoginControllers.setDefaultController(publicLogin);
        allLoginControllers.addController(UserType.ATTENDEE, null);
        allLoginControllers.addController(UserType.SPEAKER, null);
        allLoginControllers.addController(UserType.ORGANIZER, null);


        //initialize allMessengerControllers
        ControllerStorage allMessengerControllers = new ControllerStorage();

        Controller attendeeMessenger = new AttendeeMessengerController(messageManager, userManager, loginHelper);
        Controller speakerMessenger = new SpeakerMessengerController(messageManager, userManager, loginHelper);

        allMessengerControllers.setDefaultController(null);
        allMessengerControllers.addController(UserType.ATTENDEE, attendeeMessenger);
        allMessengerControllers.addController(UserType.SPEAKER, speakerMessenger);
        allMessengerControllers.addController(UserType.ORGANIZER, speakerMessenger);

        //initialize allUserControllers
        ControllerStorage allUserControllers = new ControllerStorage();

        Controller publicUser = new PublicUserController(userManager);
        Controller attendeeUser = new AttendeeUserController(userManager, loginHelper);
        Controller organizerUser = new OrganizerUserController(userManager, loginHelper);

        allUserControllers.setDefaultController(publicUser);
        allUserControllers.addController(UserType.ATTENDEE, attendeeUser);
        allUserControllers.addController(UserType.SPEAKER, attendeeUser);
        allUserControllers.addController(UserType.ORGANIZER, organizerUser);

        //initialize allEventControllers
        ControllerStorage allEventControllers = new ControllerStorage();

        Controller publicEvent = new PublicEventController(eventManager, userManager);
        Controller attendeeEvent = new AttendeeEventController(eventManager, userManager, loginHelper);
        Controller speakerEvent = new SpeakerEventController(eventManager, userManager, loginHelper);
        Controller organizerEvent = new OrganizerEventController(eventManager, userManager, loginHelper);

        allEventControllers.setDefaultController(publicEvent);
        allEventControllers.addController(UserType.ATTENDEE, attendeeEvent);
        allEventControllers.addController(UserType.SPEAKER, speakerEvent);
        allEventControllers.addController(UserType.ORGANIZER, organizerEvent);

        //initialize api
        this.api = new API(
                loginHelper,
                allLoginControllers,
                allMessengerControllers,
                allUserControllers,
                allEventControllers);

    }

    public API getAPI() {
        return this.api;
    }

}
