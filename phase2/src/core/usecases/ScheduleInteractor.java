package core.usecases;

import core.entities.Event;
import core.entities.TimeSlot;
import core.entities.User;
import core.usecases.exceptions.InvalidTimeSlotError;
import core.usecases.ports.IEventRepository;
import core.usecases.ports.IUserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleInteractor {

    private final IEventRepository events;
    private final IUserRepository users;

    public ScheduleInteractor(IEventRepository events, IUserRepository users) {
        this.events = events;
        this.users = users;
    }

    public HashMap<String, List<TimeSlot>> getUnavailableTimeSlots() {
        HashMap<String, List<TimeSlot>> result = new HashMap<>();
        for (String room: events.getRooms()) {
            List<TimeSlot> occupiedTimeSlots = new ArrayList<>();
            for (Event event: events.getEventsByRoom(room)) {
                occupiedTimeSlots.add(new TimeSlot(event.getStartTime(), event.getEndTime()));
            }
            result.put(room, occupiedTimeSlots);
        }
        return result;
    }

    public void scheduleEvent(User speaker, String room, int capacity, TimeSlot timeSlot) throws InvalidTimeSlotError {
        //validate speaker
        if (invalidEventExists(timeSlot, events.getSpeakerEvents(speaker))) {
            throw new InvalidTimeSlotError("Speaker has conflicting schedule");
        } else if (invalidEventExists(timeSlot, events.getEventsByRoom(room))) {
            throw new InvalidTimeSlotError("Room is already booked at this time");
        }
        Event event =
                new Event(speaker.getFullName(), room, capacity, timeSlot.getStartTime(), timeSlot.getEndTime());
        events.addEvent(speaker, event);
    }

    private boolean isInvalidTimeSlot(TimeSlot source, TimeSlot compare) {
        return !(source.getEndTime().isBefore(compare.getStartTime())
                || source.getEndTime().equals(compare.getStartTime())
                || source.getStartTime().isAfter(compare.getEndTime())
                || source.getStartTime().equals(compare.getEndTime()));
    }

    private boolean invalidEventExists(TimeSlot timeSlot, List<Event> events) {
        for (Event event: events) {
            TimeSlot eventTimeSlot = new TimeSlot(event.getStartTime(), event.getEndTime());
            if (isInvalidTimeSlot(timeSlot, eventTimeSlot)) {
                return true;
            }
        }
        return false;
    }
}
