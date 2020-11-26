package core.usecases.ports;

import core.entities.Event;
import core.entities.User;

import java.util.Collection;
import java.util.List;

public interface IEventRepository {

    List<Event> getSpeakerEvents(User speaker);

    List<String> getRooms();

    List<Event> getEventsByRoom(String room);

    void addEvent(User speaker, Event event);

    List<Event> getAllEvents();

    List<Event> getEnrolledEvents(User attendee);

    List<User> getAttendeesForEvent(Event event);

    void enrollAttendeeForEvent(User user, Event event);
}
