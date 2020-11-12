package controllers;

import domain.entities.Event;
import domain.usecases.EventManager;
import domain.usecases.UserManager;

import java.util.ArrayList;
import java.util.List;

public class EventController {
    private LoginHelper loginHelper;
    private EventManager eventManager;
    private UserManager userManager;
    private UserTypeChecker userTypeChecker;

    public EventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper, UserTypeChecker userTypeChecker){
        this.eventManager = eventManager;
        this.loginHelper = loginHelper;
        this.userManager = userManager;
        this.userTypeChecker = userTypeChecker;
    }

    //public permission level methods
    public List<String> getAllEventIDs() {
        List<String> allIDs = new ArrayList<String>();

        for (Event e : eventManager.getAllEvents()) allIDs.add(e.getEventId());

       return allIDs;
    }

    public String getEventSpeakerID(String eventID) {
        Event event = eventManager.getEventByID(eventID);

        if (event != null) return String.valueOf(event.getEventTime());

        return null;
    }

    public String getEventTime(String eventID) {
        Event event = eventManager.getEventByID(eventID);

        if (event != null) return String.valueOf(event.getEventTime());

        return null;
    }

    public String getEventDuration(String eventID) {
        Event event = eventManager.getEventByID(eventID);

        if (event != null) return String.valueOf(event.getEventDuration());

        return null;
    }

    //user permission level methods
    public List<String> getRegisteredEvent(String accessCode) {
        userTypeChecker.isAuthorizedUser(accessCode);
        return null;
    }

    public void registerInEvent(String accessCode, String eventID) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return;
    }

    public void unregisterInEvent(String accessCode, String eventID) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return;
    }

    //speaker permission level methods
    public List<String> getInvolvedEvent(String accessCode) {
        if (!userTypeChecker.isAuthorizedSpeaker(accessCode)) return null;
        return null;
    }

    public List<String> getEventRegisteredUsers(String accessCode, String eventID) {
        if (!userTypeChecker.isAuthorizedSpeaker(accessCode)) return null;
        return null;
    }

    //organizer permission level methods
    public String createEvent(String accessCode, String eventID, int eventDuration, int eventTime) {
        if (!userTypeChecker.isAuthorizedOrganizer(accessCode)) return null;

        //eventManager.createEvent(eventID, eventDuration, eventTime);
        return null;
    }

    public void rescheduleEvent(String accessCode, String eventID, int eventDuration, int eventTime) {
        if (!userTypeChecker.isAuthorizedOrganizer(accessCode)) return;
    }

    public void cancelEvent(String accessCode, String eventID) {
        if (!userTypeChecker.isAuthorizedOrganizer(accessCode)) return;
    }

    public void setEventSpeaker(String accessCode, String eventID, String userID) {
        if (!userTypeChecker.isAuthorizedOrganizer(accessCode)) return;
    }

}
