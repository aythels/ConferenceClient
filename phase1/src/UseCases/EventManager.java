package UseCases;

import domain.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<Event> Events;

    public EventManager() {
        this.Events = new ArrayList<Event>();
    }

    public boolean checkConflict(String eventID, int eventDuration, int eventTime){
        for (Event e: Events){
            int start = e.getEventTime();
            int end = start + e.getEventDuration();
            if ((start <= eventTime && eventTime <= end) || (start <= eventTime + eventDuration || eventTime + eventDuration <= end)){
                return false;
            }
        }
        //createEvent(eventID, eventDuration, eventTime);
        return true;
    }

    public void createEvent(String eventID, int eventDuration, int eventTime){
        Event e = new Event(eventID);
        e.setEventDuration(eventDuration);
        e.setEventTime(eventTime);
        this.Events.add(e);
    }

    public List<Event> getEvents(){
        return this.Events;
    }


}
