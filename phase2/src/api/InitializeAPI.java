package api;

import api.controllers.eventcontrollers.AttendeeEventController;
import api.controllers.eventcontrollers.OrganizerEventController;
import api.controllers.eventcontrollers.PublicEventController;
import api.controllers.eventcontrollers.SpeakerEventController;
import api.controllers.messengercontrollers.AttendeeMessengerController;
import api.controllers.messengercontrollers.SpeakerMessengerController;
import api.controllers.usercontrollers.AttendeeUserController;
import api.controllers.usercontrollers.OrganizerUserController;
import api.controllers.usercontrollers.PublicUserController;
import api.helpers.LoginHelper;
import api.controllers.logincontrollers.PublicLoginController;
import api.helpers.UserType;
import domain.usecases.EventManager;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

public class InitializeAPI {
    API api;

    /**
     * Controller classes can inherit other controller classes, hence hierarchically inheriting their methods. For example,
     * if OrganizerController inherits AttendeeController, OrganizerController contains all of AttendeeController's methods
     * in addition to it's own methods.
     */

    public InitializeAPI(MessageManager messageManager, UserManager userManager, EventManager eventManager) {
        LoginHelper loginHelper = new LoginHelper();
        this.api = new API(loginHelper, new APIHelper());

        api.createGroup("event_controller");
        api.add("event_controller", new PublicEventController(eventManager, userManager));
        api.add("event_controller", new AttendeeEventController(eventManager, userManager, loginHelper), UserType.ATTENDEE);
        api.add("event_controller", new SpeakerEventController(eventManager, userManager, loginHelper), UserType.SPEAKER);
        api.add("event_controller", new OrganizerEventController(eventManager, userManager, loginHelper), UserType.ORGANIZER);

        api.createGroup("login_controller");
        api.add("login_controller", new PublicLoginController(userManager, loginHelper));

        api.createGroup("messenger_controller");
        api.add("messenger_controller", new AttendeeMessengerController(messageManager, userManager, loginHelper), UserType.ATTENDEE);
        api.add("messenger_controller", new SpeakerMessengerController(messageManager, userManager, loginHelper), UserType.SPEAKER);
        api.add("messenger_controller", new SpeakerMessengerController(messageManager, userManager, loginHelper), UserType.ORGANIZER);

        api.createGroup("user_controller");
        api.add("user_controller", new PublicUserController(userManager));
        api.add("user_controller", new AttendeeUserController(userManager, loginHelper), UserType.ATTENDEE);
        api.add("user_controller", new AttendeeUserController(userManager, loginHelper), UserType.SPEAKER);
        api.add("user_controller", new OrganizerUserController(userManager, loginHelper), UserType.ORGANIZER);

    }

    public API getAPI() {
        return this.api;
    }

}
