package gateways;

import core.entities.Event;
import core.entities.User;
import core.usecases.ports.IEventRepository;

import java.util.List;

public class EventRepository extends AbstractRepository implements IEventRepository {

    public EventRepository(String path) {
        super(path);
    }

    @Override
    public List<Event> getSpeakerEvents(User speaker) {
        return null;
    }

    @Override
    public List<String> getRooms() {
        return null;
    }

    @Override
    public List<Event> getEventsByRoom(String room) {
        return null;
    }

    @Override
    public void addEvent(User speaker, Event event) {

    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public List<Event> getEnrolledEvents(User attendee) {
        return null;
    }

    @Override
    public List<User> getAttendeesForEvent(Event event) {
        return null;
    }

    @Override
    public void enrollAttendeeForEvent(User user, Event event) {

    }
}
