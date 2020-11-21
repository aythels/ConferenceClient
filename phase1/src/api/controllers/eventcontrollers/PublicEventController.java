package api.controllers.eventcontrollers;

import domain.entities.Event;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

public class PublicEventController extends EventController {
    protected final EventManager eventManager;
    protected final UserManager userManager;

    /**
     * @param eventManager Use case class responsible for handing everything related to events.
     * @param userManager Use case class responsible for handing everything related to events.
     */

    public PublicEventController(EventManager eventManager, UserManager userManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
    }

    /**
     * Get all current event IDs
     * @return  a list of event IDs
     */

    public List<Integer> getAllEventIDs() {
        List<Integer> allEventIDs = new ArrayList<>();

        for (Event e : eventManager.getAllEvents()) {
            int eventID = eventManager.getIdByEvent(e);
            allEventIDs.add(eventID);
        }

        return allEventIDs;
    }

    /**
     * Get a list of all speakers at an event
     * @param eventID   the event's identifier
     * @return  a list of ids of users speaking at this event, null if the event was not found
     */

    public List<String> getEventSpeakerID(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return null;

        ArrayList<User> allSpeakers = eventManager.getSpeakerById(eventID);
        ArrayList<String> allUserIDs = new ArrayList<>();

        for (User u : allSpeakers) allUserIDs.add(userManager.getID(u));
        return allUserIDs;

    }

    public List<String> getEventSpeakerName(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return null;

        ArrayList<User> allSpeakers = eventManager.getSpeakerById(eventID);
        ArrayList<String> allUserNames = new ArrayList<>();

        for (User u : allSpeakers) allUserNames.add(userManager.getName(u));
        return allUserNames;

    }

    /**
     * Get the display name of the event by its identifier
     * @param eventID   the event's identifier
     * @return  the display name of the event, null if the event is not found
     */

    public String getEventName(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return null;

        return eventManager.getEventNameById(eventID);
    }

    /**
     * Get the time that this event takes place
     * @param eventID   the event's identifier
     * @return  the time this event takes place, in milliseconds since Unix Epoch, null if event is not found
     */

    public Integer getEventTime(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return null;

        //this method calls getEventTime() directly from the Event object instead of using the use case
        Event event = eventManager.getEventByID(eventID);
        return event.getEventTime();
    }

    /**
     * Get the duration of the event
     * @param eventID   the event's identifier
     * @return  the duration of the event, in milliseconds, null if event is not found
     */

    public Integer getEventDuration(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return null;

        //this method calls getEventDuration() directly from the Event object instead of using the use case
        Event event = eventManager.getEventByID(eventID);
        return event.getEventDuration();
    }

}
