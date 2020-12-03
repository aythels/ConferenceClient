package gateways.stores;

import core.entities.Event;
import core.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStore implements Serializable {

    private final Map<String, List<Event>> eventsByRoom;
    private final Map<User, List<Event>> eventsBySpeaker;
    private final List<Event> allEvents;
    private final Map<Event, List<User>> eventAttendees;

    public EventStore() {
        eventsByRoom = new HashMap<>();
        eventsBySpeaker = new HashMap<>();
        allEvents = new ArrayList<>();
        eventAttendees = new HashMap<>();
    }

    public Map<String, List<Event>> getEventsByRoom() {
        return eventsByRoom;
    }

    public Map<User, List<Event>> getEventsBySpeaker() {
        return eventsBySpeaker;
    }

    public List<Event> getAllEvents() {
        return allEvents;
    }

    public Map<Event, List<User>> getEventAttendees() {
        return eventAttendees;
    }
}
