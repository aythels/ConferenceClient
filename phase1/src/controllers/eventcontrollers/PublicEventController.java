package controllers.eventcontrollers;

import domain.entities.Event;
import domain.usecases.EventManager;

import java.util.ArrayList;
import java.util.List;

class PublicEventController extends EventController {
    protected final EventManager eventManager;

    protected PublicEventController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public List<String> getAllEventIDs() {
        List<String> allIDs = new ArrayList<String>();

        for (Event e : eventManager.getAllEvents()) allIDs.add(e.getEventId());

        return allIDs;
    }

    public String getEventSpeakerID(String eventID) {
        Event event = eventManager.getEventByID(eventID);

        //if (event != null) return eventManager.getEventSpeakerID(event);

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

}
