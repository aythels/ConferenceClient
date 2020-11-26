package core.usecases.ports;

import core.entities.Event;
import core.entities.User;

import java.util.List;

public interface IEventRepository {

    List<Event> getSpeakerEvents(User speaker);

    List<String> getRooms();

    List<Event> getEventsByRoom(String room);

    void addEvent(User speaker, Event event);
}
