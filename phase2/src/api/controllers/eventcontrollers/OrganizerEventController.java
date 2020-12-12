package api.controllers.eventcontrollers;

import api.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

public class OrganizerEventController extends SpeakerEventController {

    /**
     * @param eventManager Use case class responsible for handing everything related to events.
     * @param userManager Use case class responsible for handing everything related to events.
     * @param loginHelper Helper class that keeps track of logged in users.
     */

    public OrganizerEventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper) {
        super(eventManager, userManager, loginHelper);
    }

    /**
     * Creates a new event
     * @param eventName the display name of the event.
     * @param eventDuration the duration of the event, in milliseconds.
     * @param eventTime  the time this event takes place, in milliseconds since Unix Epoch.
     * @param capacity capacity for the event, in people.
     * @return  true if event creation was successful, false otherwise.
     */

    public boolean createEvent(String eventName, Integer eventDuration, Integer eventTime, Integer capacity) {
        return eventManager.createEvent(eventDuration, eventTime, eventName, capacity);
    }

    /**
     * Reschedule an existing event
     * @param eventID   the event's identifier
     * @param eventDuration the duration of the event, in milliseconds.
     * @param eventTime  the time this event takes place, in milliseconds since Unix Epoch.
     * @return  true if the event was found and rescheduled successfully, false otherwise.
     */

    public boolean rescheduleEvent(int eventID, int eventDuration, int eventTime) {
        if (eventManager.getEventByID(eventID) == null) return false;

        return eventManager.rescheduleEvent(eventID, eventDuration, eventTime);
    }

    /**
     * Cancel an existing event
     * @param eventID   the event's identifier.
     * @return  true if the event was found and canceled successfully, false otherwise.
     */

    public boolean cancelEvent(int eventID) {
        if (eventManager.getEventByID(eventID) == null) return false;

        return eventManager.cancelEvent(eventID);
    }

    /**
     * Set the speaker for an event.
     * @param eventID   the event's identifier.
     * @param userID    the specified user's identifier.
     * @return  true if the event and user were found, and user was set as speaker successfully, false otherwise.
     */

    public boolean setEventSpeaker(Integer eventID, String userID) {
        if (eventManager.getEventByID(eventID) == null) return false;
        if (!userManager.userExists(userID)) return false;

        User speaker = userManager.getUser(userID);
        return eventManager.bookSpeaker(speaker, eventID);
    }
}
