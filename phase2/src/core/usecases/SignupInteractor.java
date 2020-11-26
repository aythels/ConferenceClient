package core.usecases;

import core.entities.Event;
import core.entities.User;
import core.usecases.exceptions.EventFullError;
import core.usecases.ports.IEventRepository;
import core.usecases.ports.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class SignupInteractor {

    private IEventRepository events;
    private IUserRepository users;

    public SignupInteractor(IEventRepository events, IUserRepository users) {
        this.events = events;
        this.users = users;
    }

    public List<Event> getEventListForAttendee(User attendee) {
        List<Event> result = new ArrayList<>();
        for (Event event: events.getAllEvents()) {
            if (!events.getEnrolledEvents(attendee).contains(event)) {
                result.add(event);
            }
        }
        return result;
    }

    public void signUpAttendee(User attendee, Event event) throws EventFullError {
        if (events.getAttendeesForEvent(event).size() == event.getCapacity()) {
            throw new EventFullError("Event is fully booked");
        } else {
            events.enrollAttendeeForEvent(attendee, event);
        }
    }
}
