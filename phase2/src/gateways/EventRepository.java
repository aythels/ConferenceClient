package gateways;

import core.entities.Event;
import core.entities.User;
import core.usecases.ports.IEventRepository;
import gateways.stores.EventStore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventRepository extends AbstractRepository implements IEventRepository {

    private final File file;
    private final EventStore store;

    public EventRepository(String path) throws IOException {
        super(path);
        file = new File(path, "events.ser");
        if (file.createNewFile()) {
            store = new EventStore();
            put(file, store);
        } else {
            store = (EventStore) get(file);
        }
    }

    @Override
    public List<Event> getSpeakerEvents(User speaker) {
        return store.getEventsBySpeaker().get(speaker);
    }

    @Override
    public List<String> getRooms() {
        return new ArrayList<>(store.getEventsByRoom().keySet());
    }

    @Override
    public List<Event> getEventsByRoom(String room) {
        return store.getEventsByRoom().get(room);
    }

    @Override
    public void addEvent(User speaker, Event event) {
        addToAll(event);
        addToSpeaker(speaker, event);
        addToRoom(event.getRoom(), event);
        addToAttendees(event);
        put(file, store);
    }

    private void addToAll(Event event) {
        store.getAllEvents().add(event);
    }

    private void addToSpeaker(User speaker, Event event) {
        if (store.getEventsBySpeaker().containsKey(speaker)) {
            store.getEventsBySpeaker().get(speaker).add(event);
        } else {
            List<Event> events = new ArrayList<>();
            events.add(event);
            store.getEventsBySpeaker().put(speaker, events);
        }
    }

    private void addToRoom(String room, Event event) {
        if (store.getEventsByRoom().containsKey(room)) {
            store.getEventsByRoom().get(room).add(event);
        } else {
            List<Event> events = new ArrayList<>();
            events.add(event);
            store.getEventsByRoom().put(room, events);
        }
    }

    private void addToAttendees(Event event) {
        store.getEventAttendees().put(event, new ArrayList<>());
    }

    @Override
    public List<Event> getAllEvents() {
        return store.getAllEvents();
    }

    @Override
    public List<Event> getEnrolledEvents(User attendee) {
        List<Event> result = new ArrayList<>();
        for (Event event: store.getAllEvents()) {
            if (store.getEventAttendees().get(event).contains(attendee)) {
               result.add(event);
            }
        }
        return result;
    }

    @Override
    public List<User> getAttendeesForEvent(Event event) {
        return store.getEventAttendees().get(event);
    }

    @Override
    public void enrollAttendeeForEvent(User user, Event event) {
        store.getEventAttendees().get(event).add(user);
        put(file, store);
    }
}
