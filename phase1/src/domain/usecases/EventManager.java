package domain.usecases;

import domain.entities.User;
import domain.entities.Event;
import java.util.ArrayList;

import java.util.List;

public class EventManager {

    private HashMapManager registered;
    private Integer eventId = 1;
    private int maxPeople = 2;

    public EventManager() {
        this.registered = new HashMapManager();
    }

    public Event getEventByID(String eventID) {
        List<Event> allEvents = getAllEvents();
        for (Event e : allEvents) if (e.getEventId() == eventID) return e;
        return null;
    }

    public boolean checkConflict(int eventDuration, int eventTime){
        for (Integer i: this.registered.getKeySet()){
            Event e = this.registered.getEventbyId(i);
            int start = e.getEventTime();
            int end = start + e.getEventDuration();
            if (checkConflictHelper(start, end, eventDuration, eventTime)){
                return false;
            }
        }
        return true;
    }

    private boolean checkConflictHelper(int start, int end, int eventDuration, int eventTime){
        if (start <= eventTime && eventTime <= end){
            return true;
        }
        if (start <= eventTime + eventDuration && eventTime + eventDuration <= end){
            return true;
        }
        return false;
    }

    public String createEvent(int eventDuration, int eventTime){
        if(this.checkConflict(eventDuration, eventTime)) {
            Event e = new Event(this.eventId.toString());
            e.setEventDuration(eventDuration);
            e.setEventTime(eventTime);
            this.registered.addEvent(this.eventId, eventDuration, eventTime, e);
            this.eventId += 1;
            return "The event is now created.";
        }
        return "Sorry, there is conflict with other events.";
    }

    public String rescheduleEvent(int id){
        Event e = this.registered.getEventbyId(id);
        int eventDuration = e.getEventDuration();
        int eventTime = e.getEventTime();
        this.cancelEvent(id);
        if(this.createEvent(eventDuration, eventTime) == "Sorry, there is conflict with other events."){
            return "Sorry, reschedule failed, there is conflict with other events.";
        }
        return "The event is now rescheduled, all registered speakers and attendees will need to register again for this event.";
    }

    public ArrayList<Event> getAllEvents(){
        ArrayList<Event> listEvent = new ArrayList<>();
        for (Integer i: this.registered.getKeySet()){
            Event e = this.registered.getEventbyId(i);
            listEvent.add(e);
        }
        return listEvent;
    }


    public String bookForAttendee(User u, int id){
        if (!this.registered.ifInKeySet(id)){
            return "Event does not exist.";
        }
        ArrayList<User> booked = this.registered.getAttendeeById(id);
        if (booked.size() <= 3){
            if (booked.contains(u)){
                return "You are already registered for this event.";
            }
            booked.add(u);
            this.registered.updateAttendee(id, booked);
            return "You are now registered as attendee for this event.";
        }
        return "Sorry, the room is full.";
    }

    public String unBookForAttendee(User u, int id){
        if (!this.registered.ifInKeySet(id)){
            return "Sorry, the event does not exist.";
        }
        ArrayList<User> booked = this.registered.getAttendeeById(id);
        if (!booked.contains(u)){
            return "Sorry, you are not registered.";
        }
        booked.remove(u);
        this.registered.updateAttendee(id, booked);
        return "You are no longer registered for this event.";
    }

    public String bookSpeaker(User u, int id){
        if (!u.getUserType().equals("Speaker")){
            return "You cannot register as speaker for this event.";
        }
        if (this.registered.updateSpeaker(id, u)){
            return "You are now registered as speaker for this event";
        }
        return "There is already a speaker registered for this event!";
    }

    public String cancelEvent(int id){
        if (!this.registered.ifInKeySet(id)){
            return "Sorry, the event does not exist";
        }
        this.registered.cancelEventById(id);
        return "The event is now cancelled";
    }
}
