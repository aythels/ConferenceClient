package controllers.eventcontrollers;

import controllers.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

class SpeakerEventController extends UserEventController{

    protected SpeakerEventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper) {
        super(eventManager, userManager, loginHelper);
    }

    /**
     * Get a list of all users registered in an event
     * @param eventID   the event's identifier
     * @return  a list of user ids of users registered in this event, null if the event is not found
     */

    public List<String> getEventRegisteredUserIDs(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return null;

        List<String> allUserIDs = new ArrayList<String>();
        List<User> allUsers = eventManager.getAttendeesById(eventID);

        for (User u : allUsers) allUserIDs.add(userManager.getID(u));

        return allUserIDs;
    }
}
