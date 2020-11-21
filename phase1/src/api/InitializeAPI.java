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
    private final API api;

    /**
     * Initializes an instance of API. Comments in constructor explain step by step process.
     * @param messageManager Use case class responsible for handing everything related to messaging.
     * @param userManager Use case class responsible for handing everything related to events.
     * @param eventManager Use case class responsible for handing everything related to events.
     */

    public InitializeAPI(MessageManager messageManager, UserManager userManager, EventManager eventManager){

        //Initialize the LoginHelper utility class
        LoginHelper loginHelper = new LoginHelper();

        //Initialize the ControllerStorage class and all related controllers related to logging in.
        ControllerStorage allLoginControllers = new ControllerStorage();

        Controller publicLogin = new PublicLoginController(userManager, loginHelper);

        //Put the controllers inside controllerStorage depending on what user type they are intended for.
        allLoginControllers.setDefaultController(publicLogin);
        allLoginControllers.addController(UserType.ATTENDEE, null);
        allLoginControllers.addController(UserType.SPEAKER, null);
        allLoginControllers.addController(UserType.ORGANIZER, null);


        //Initialize the ControllerStorage class and all related controllers related to messaging.
        ControllerStorage allMessengerControllers = new ControllerStorage();

        Controller attendeeMessenger = new AttendeeMessengerController(messageManager, userManager, loginHelper);
        Controller speakerMessenger = new SpeakerMessengerController(messageManager, userManager, loginHelper);

        //Put the controllers inside controllerStorage depending on what user type they are intended for.
        allMessengerControllers.setDefaultController(null);
        allMessengerControllers.addController(UserType.ATTENDEE, attendeeMessenger);
        allMessengerControllers.addController(UserType.SPEAKER, speakerMessenger);
        allMessengerControllers.addController(UserType.ORGANIZER, speakerMessenger);

        //Initialize the ControllerStorage class and all related controllers related to user management.
        ControllerStorage allUserControllers = new ControllerStorage();

        Controller publicUser = new PublicUserController(userManager);
        Controller attendeeUser = new AttendeeUserController(userManager, loginHelper);
        Controller organizerUser = new OrganizerUserController(userManager, loginHelper);

        //Put the controllers inside controllerStorage depending on what user type they are intended for.
        allUserControllers.setDefaultController(publicUser);
        allUserControllers.addController(UserType.ATTENDEE, attendeeUser);
        allUserControllers.addController(UserType.SPEAKER, attendeeUser);
        allUserControllers.addController(UserType.ORGANIZER, organizerUser);

        //Initialize the ControllerStorage class and all related controllers related to events.
        ControllerStorage allEventControllers = new ControllerStorage();

        Controller publicEvent = new PublicEventController(eventManager, userManager);
        Controller attendeeEvent = new AttendeeEventController(eventManager, userManager, loginHelper);
        Controller speakerEvent = new SpeakerEventController(eventManager, userManager, loginHelper);
        Controller organizerEvent = new OrganizerEventController(eventManager, userManager, loginHelper);

        //Put the controllers inside controllerStorage depending on what user type they are intended for.
        allEventControllers.setDefaultController(publicEvent);
        allEventControllers.addController(UserType.ATTENDEE, attendeeEvent);
        allEventControllers.addController(UserType.SPEAKER, speakerEvent);
        allEventControllers.addController(UserType.ORGANIZER, organizerEvent);

        //initialize the api
        this.api = new API(
                loginHelper,
                allLoginControllers,
                allMessengerControllers,
                allUserControllers,
                allEventControllers);

    }

    /**
     * Get the initialized API.
     * @return an instance of API as initialized in this class's constructor.
     */

    public API getAPI() {
        return this.api;
    }

}
