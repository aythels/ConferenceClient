package controllers.eventcontrollers;

import controllers.LoginHelper;
import domain.usecases.EventManager;

class OrganizerEventController extends SpeakerEventController {

    protected OrganizerEventController(EventManager eventManager, LoginHelper loginhelper) {
        super(eventManager, loginhelper);
    }

    public String createEvent(String accessCode, String eventID, int eventDuration, int eventTime) {
        return null;
    }

    public boolean rescheduleEvent(String accessCode, String eventID, int eventDuration, int eventTime) {
        return false;
    }

    public boolean cancelEvent(String accessCode, String eventID) {
        return false;
    }

    public boolean setEventSpeaker(String accessCode, String eventID, String userID) {
        return false;
    }
}
