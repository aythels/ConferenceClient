package core.usecases.ports;

import core.entities.Event;
import core.entities.User;

import java.util.List;

public interface IEventRepository {

    List<Event> getSpeakerEvents(User speaker);
}
