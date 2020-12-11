package api.controllers.eventcontrollers;

import api.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

public class AttendeeEventController extends PublicEventController {
    protected final LoginHelper loginHelper;

    /**
     * @param eventManager Use case class responsible for handing everything related to events.
     * @param userManager Use case class responsible for handing everything related to events.
     * @param loginHelper Helper class that keeps track of logged in users.
     */

    public AttendeeEventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper) {
        super(eventManager, userManager);
        this.loginHelper = loginHelper;
    }

    /**
     * Get all events the user has registered in
     * @param accessCode    a code unique to the current client
     * @return a list of ids of events the user has registered in, null if invalid accessCode
     */

    public List<Integer> getAllRegisteredEventIDs(String accessCode) {
        if (!loginHelper.isValidAccessCode(accessCode)) return null;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        List<Integer> allEventIDs = eventManager.getEventIdsByUser(thisUser);

        return new ArrayList<>(allEventIDs);
    }

    /**
     * Register the current client user in the specified event
     * @param accessCode    a code unique to the current client
     * @param eventID   the event's identifier
     * @return true if the registration was successful, false otherwise (etc invalid parameters or other reasons)
     */

    public boolean registerInEvent(String accessCode, Integer eventID) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;
        if (eventManager.getEventByID(eventID) == null) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        return eventManager.bookForAttendee(thisUser, eventID);
    }

    /**
     * Register the current client user in the specified event
     * @param accessCode    a code unique to the current client
     * @param eventID   the event's identifier
     * @return true if the un-registration was successful, false otherwise(etc invalid parameters or other reasons)
     */

    public boolean unregisterInEvent(String accessCode, int eventID) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;
        if (eventManager.getEventByID(eventID) == null) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        return eventManager.unBookForAttendee(thisUser, eventID);
    }

}
