package controllers.eventcontrollers;

import controllers.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

class UserEventController extends PublicEventController {
    protected final LoginHelper loginHelper;

    protected UserEventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper) {
        super(eventManager, userManager);
        this.loginHelper = loginHelper;
    }

    /**
     * Get all events the user has registered in
     * @param accessCode    a code unique to the current client
     * @return a list of ids of events the user has registered in, null if invalid accessCode
     */

    public List<String> getAllRegisteredEventIDs(String accessCode) {
        if (!loginHelper.isValidAccessCode(accessCode)) return null;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        List<String> allEventIDs = new ArrayList<String>();
        List<Integer> allEventIDsInt = eventManager.getEventIdsByUser(thisUser);

        for (Integer i : allEventIDsInt) allEventIDs.add(String.valueOf(i));
        return allEventIDs;
    }

    /**
     * Register the current client user in the specified event
     * @param accessCode    a code unique to the current client
     * @param eventID   the event's identifier
     * @return true if the registration was successful, false otherwise (etc invalid parameters or other reasons)
     */

    public boolean registerInEvent(String accessCode, String eventID) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;
        if (eventManager.getEventByID(Integer.parseInt(eventID)) == null) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        int eID = Integer.parseInt(eventID);

        return eventManager.bookForAttendee(thisUser, eID);
    }

    /**
     * Register the current client user in the specified event
     * @param accessCode    a code unique to the current client
     * @param eventID   the event's identifier
     * @return true if the un-registration was successful, false otherwise(etc invalid parameters or other reasons)
     */

    public boolean unregisterInEvent(String accessCode, String eventID) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;
        if (eventManager.getEventByID(Integer.parseInt(eventID)) == null) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        int eID = Integer.parseInt(eventID);

        return eventManager.unBookForAttendee(thisUser, eID);
    }

}
