package controllers.eventcontrollers;

import domain.entities.Event;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

class PublicEventController extends EventController {
    protected final EventManager eventManager;
    protected final UserManager userManager;

    protected PublicEventController(EventManager eventManager, UserManager userManager) {
        this.eventManager = eventManager;
        this.userManager = userManager;
    }

    /**
     * Get all current event IDs
     * @return  a list of event IDs
     */

    public List<String> getAllEventIDs() {
        List<String> allEventIDs = new ArrayList<String>();

        for (Event e : eventManager.getAllEvents()) {
            String eventID = String.valueOf(eventManager.getIdByEvent(e));
            allEventIDs.add(eventID);
        }

        return allEventIDs;
    }

    /**
     * Get a list of all speakers at an event
     * @param eventID   the event's identifier
     * @return  a list of ids of users speaking at this event, null if the event was not found
     */

    public List<String> getEventSpeakerID(String eventID) {
        if (eventManager.getEventByID(Integer.parseInt(eventID)) == null) return null;

        ArrayList<User> allSpeakers = eventManager.getSpeakerById(Integer.parseInt(eventID));
        ArrayList<String> allUserIDs = new ArrayList<String>();

        for (User u : allSpeakers) allUserIDs.add(userManager.getID(u));
        return allUserIDs;

    }

    /**
     * Get the display name of the event by its identifier
     * @param eventID   the event's identifier
     * @return  the display name of the event, null if the event is not found
     */

    public String getEventName(String eventID) {
        if (eventManager.getEventByID(Integer.parseInt(eventID)) == null) return null;

        String name = eventManager.getEventNameById(Integer.parseInt(eventID));
        return name;
    }

    /**
     * Get the time that this event takes place
     * @param eventID   the event's identifier
     * @return  the time this event takes place, in milliseconds since Unix Epoch, null if event is not found
     */

    public String getEventTime(String eventID) {
        if (eventManager.getEventByID(Integer.parseInt(eventID)) == null) return null;

        //this method calls getEventTime() directly from the Event object instead of using the use case
        Event event = eventManager.getEventByID(Integer.parseInt(eventID));
        return String.valueOf(event.getEventTime());
    }

    /**
     * Get the duration of the event
     * @param eventID   the event's identifier
     * @return  the duration of the event, in milliseconds, null if event is not found
     */

    public String getEventDuration(String eventID) {
        if (eventManager.getEventByID(Integer.parseInt(eventID)) == null) return null;

        //this method calls getEventDuration() directly from the Event object instead of using the use case
        Event event = eventManager.getEventByID(Integer.parseInt(eventID));
        return String.valueOf(event.getEventDuration());
    }

}
