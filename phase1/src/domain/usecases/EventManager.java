package domain.usecases;

import domain.entities.User;
import domain.entities.Event;
import java.util.ArrayList;

import java.util.HashMap;
import javafx.util.Pair;

public class EventManager {

    private HashMapManager registered;
    private Integer eventId = 1;
    private int maxPeople = 2;

    public EventManager() {
        this.registered = new HashMapManager();
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

    public boolean createEvent(int eventDuration, int eventTime, String eventName){
        if(this.checkConflict(eventDuration, eventTime)) {
            Event e = new Event(this.eventId.toString());
            e.setEventDuration(eventDuration);
            e.setEventTime(eventTime);
            e.setEventName(eventName);
            this.registered.addEvent(this.eventId, eventDuration, eventTime, e);
            this.eventId += 1;
            return true;
        }
        return false;
    }

    public boolean rescheduleEvent(int id){
        Event e = this.registered.getEventbyId(id);
        int eventDuration = e.getEventDuration();
        int eventTime = e.getEventTime();
        String eventName = e.getEventName();
        this.cancelEvent(id);
        if(this.createEvent(eventDuration, eventTime, eventName) == false){
            return false;
        }
        return true;
    }

    public ArrayList<Event> getAllEvents(){
        ArrayList<Event> listEvent = new ArrayList<>();
        for (Integer i: this.registered.getKeySet()){
            Event e = this.registered.getEventbyId(i);
            listEvent.add(e);
        }
        return listEvent;
    }


    public boolean bookForAttendee(User u, int id){
        if (!this.registered.ifInKeySet(id)){
            return false;
        }
        ArrayList<User> booked = this.registered.getAttendeeById(id);
        if (booked.size() < this.maxPeople){
            if (booked.contains(u)){
                return false;
            }
            booked.add(u);
            this.registered.updateAttendee(id, booked);
            return true;
        }
        return false;
    }

    public boolean unBookForAttendee(User u, int id){
        if (!this.registered.ifInKeySet(id)){
            return false;
        }
        ArrayList<User> booked = this.registered.getAttendeeById(id);
        if (!booked.contains(u)){
            return false;
        }
        booked.remove(u);
        this.registered.updateAttendee(id, booked);
        return true;
    }

    public boolean bookSpeaker(User u, int id){
        if (!u.getUserType().equals("Speaker")){
            return false;
        }
        if (this.registered.updateSpeaker(id, u)){
            return true;
        }
        return false;
    }

    public boolean cancelEvent(int id){
        if (!this.registered.ifInKeySet(id)){
            return false;
        }
        this.registered.cancelEventById(id);
        return true;
    }

    public Integer getIdByEvent(Event e){
        for (int i: this.registered.getKeySet()){
            if (this.registered.getEventbyId(i) == e){
                return i;
            }
        }
        return 0;
    }

    public ArrayList<User> getAttendeeById(int id){
        if (!this.registered.getKeySet().contains(id)){
            return null;
        }
        return this.registered.getAttendeeById(id);
    }

    public ArrayList<User> getSpeakerById(int id){
        if (!this.registered.getKeySet().contains(id)){
            return null;
        }
        return this.registered.getSpeakerById(id);
    }

    public ArrayList<Integer> getEventIdByUser(User u){
        ArrayList<Integer> listOfEventId = new ArrayList<>();
        for (Integer i: this.registered.getKeySet()){
            if (this.registered.getAttendeeById(i).contains(u)){
                listOfEventId.add(i);
            }
        }
        return listOfEventId;
    }

    public String getEventNameById(int id){
        if (!this.registered.getKeySet().contains(id)){
            return null;
        }
        return this.registered.getEventbyId(id).getEventName();
    }

}
