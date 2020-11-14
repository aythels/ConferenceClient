package controllers.eventcontrollers;

import controllers.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

class OrganizerEventController extends SpeakerEventController {

    protected OrganizerEventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper) {
        super(eventManager, userManager, loginHelper);
    }

    /**
     * Creates a new event
     * @param eventName the display name of the event.
     * @param eventDuration the duration of the event, in milliseconds.
     * @param eventTime  the time this event takes place, in milliseconds since Unix Epoch.
     * @return  true if event creation was successful, false otherwise.
     */

    public boolean createEvent(String eventName, String eventDuration, String eventTime) {
        int _eventDuration = Integer.parseInt(eventDuration);
        int _eventTime = Integer.parseInt(eventTime);

        if (_eventDuration <= 0) return false;

        return eventManager.createEvent(_eventDuration, _eventTime, eventName);
    }

    /**
     * Reschedule an existing event
     * @param eventID   the event's identifier
     * @param eventDuration the duration of the event, in milliseconds.
     * @param eventTime  the time this event takes place, in milliseconds since Unix Epoch.
     * @return  true if the event was found and rescheduled successfully, false otherwise.
     */

    public boolean rescheduleEvent(String eventID, String eventDuration, String eventTime) {
        int _eventDuration = Integer.parseInt(eventDuration);
        int _eventTime = Integer.parseInt(eventTime);
        int _eventID = Integer.parseInt(eventID);

        if (eventManager.getEventByID(_eventID) == null) return false;
        if (_eventDuration <= 0) return false;

        return eventManager.rescheduleEvent(_eventID, _eventDuration, _eventTime);
    }

    /**
     * Cancel an existing event
     * @param eventID   the event's identifier.
     * @return  true if the event was found and canceled successfully, false otherwise.
     */

    public boolean cancelEvent(String eventID) {
        int _eventID = Integer.parseInt(eventID);

        if (eventManager.getEventByID(_eventID) == null) return false;

        return eventManager.cancelEvent(_eventID);
    }

    /**
     * Set the speaker for an event.
     * @param eventID   the event's identifier.
     * @param userID    the specified user's identifier.
     * @return  true if the event and user were found, and user was set as speaker successfully, false otherwise.
     */

    public boolean setEventSpeaker(String eventID, String userID) {
        int _eventID = Integer.parseInt(eventID);
        User speaker = userManager.getUser(userID);

        if (eventManager.getEventByID(_eventID) == null) return false;
        if (speaker == null) return false;

        return eventManager.bookSpeaker(speaker, _eventID);
    }
}
