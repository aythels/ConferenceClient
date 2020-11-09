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

    public EventController(EventManager eventManager, UserManager userManager, LoginHelper loginHelper){
        this.eventManager = eventManager;
        this.loginHelper = loginHelper;
        this.userManager = userManager;
    }

    //public permission level methods
    public List<String> getAllEvents() {
        List<String> idArray = new ArrayList<String>();

        List<Event> allEvents = eventManager.getEvents();
        for (Event e : allEvents) {
            idArray.add(e.getEventID());
        }


       return idArray;
    }

    public String getEventSpeaker(String eventID) {
        return eventManager.getEventSpeaker(getEventByID(eventID));
    }

    public int getEventTime(String eventID) {
        return getEventByID(eventID).getEventTime();
    }

    public int getEventDuration(String eventID) {
        return eventManager.getEventByID(eventID).getEventDuration();
    }

    public Event getEventByID(String eventID) {
        List<Event> allEvents = eventManager.getEvents();
        for (Event e : allEvents) if (e.getEventID() == eventID) return e;
        return null;
    }

    //user permission level methods
    public List<String> getRegisteredEvent(String accessCode) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return;
    }

    public void registerInEvent(String accessCode, String eventID) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return;
    }

    public void unregisterInEvent(String accessCode, String eventID) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return;
    }

    //speaker permission level methods
    public List<String> getInvolvedEvent(String accessCode) {
        if (!loginHelper.isAuthorizedSpeaker(accessCode)) return null;
    }

    public List<String> getEventRegisteredUsers(String accessCode, String eventID) {
        if (!loginHelper.isAuthorizedSpeaker(accessCode)) return null;
    }

    //organizer permission level methods
    public String createEvent(String accessCode, String eventID, int eventDuration, int eventTime) {
        if (!loginHelper.isAuthorizedOrganizer(accessCode)) return null;

        eventManager.createEvent(eventID, eventDuration, eventTime);
    }

    public void rescheduleEvent(String accessCode, String eventID, int eventDuration, int eventTime) {
        if (!loginHelper.isAuthorizedOrganizer(accessCode)) return;
    }

    public void cancelEvent(String accessCode, String eventID) {
        if (!loginHelper.isAuthorizedOrganizer(accessCode)) return;
    }

    public void setEventSpeaker(String accessCode, String eventID, String userID) {
        if (!loginHelper.isAuthorizedOrganizer(accessCode)) return;
    }

}
