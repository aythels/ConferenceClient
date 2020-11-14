package controllers.eventcontrollers;

import controllers.LoginHelper;
import domain.entities.Event;
import domain.entities.User;
import domain.usecases.EventManager;

import java.util.ArrayList;
import java.util.List;

class UserEventController extends PublicEventController {
    protected final LoginHelper loginHelper;

    protected UserEventController(EventManager eventManager, LoginHelper loginhelper) {
        super(eventManager);
        this.loginHelper = loginhelper;
    }

    public List<String> getAllRegisteredEventIDs(String accessCode) {
        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        List<String> allEventIDs = new ArrayList<String>();
        //List<Event> allEvents = eventManager.getAllRegisteredEventsByUser();

        //for (Event e : allEvents) allEventIDs.add(e.getEventId());

        return allEventIDs;
    }

    public boolean registerInEvent(String accessCode, String eventID) {
        Event event = eventManager.getEventByID(eventID);

        //if (event != null) return eventManager.registerEvent(event);
        return false;
    }

    public boolean unregisterInEvent(String accessCode, String eventID) {
        Event event = eventManager.getEventByID(eventID);

        //if (event != null) return eventManager.unregisterEvent(event);
        return false;
    }

}
