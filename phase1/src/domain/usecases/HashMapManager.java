package domain.usecases;

import domain.entities.Event;
import domain.entities.User;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class HashMapManager implements Serializable {
    private HashMap<Integer, Pair<Pair<Event, ArrayList<User>>, Pair<Integer, ArrayList<User>>>> hashMap;

    public HashMapManager(){
        this.hashMap = new HashMap<>();
    }

    public Event getEventById(int id){
        return this.hashMap.get(id).getKey().getKey();
    }

    public Set<Integer> getKeySet(){
        return this.hashMap.keySet();
    }

    public boolean ifInKeySet(int i){
        if (this.getKeySet().contains(i)){
            return true;
        }
        return false;
    }

    public Integer getCapacityById(int id){
        return this.hashMap.get(id).getValue().getKey();
    }

    public void addEvent(int eventId, int maximum, Event e){
        this.hashMap.put(eventId, new Pair(new Pair(e, new ArrayList<User>()), new Pair(maximum, new ArrayList<User>())));
    }

    public void addEvent(int eventId, Event e){
        this.hashMap.put(eventId, new Pair(new Pair(e, new ArrayList<User>()), new Pair(2, new ArrayList<User>())));
    }

    public ArrayList<User> getAttendeeById(int id){
        return this.hashMap.get(id).getValue().getValue();
    }


    public void updateAttendee(int id, ArrayList<User> booked){
        Pair<Event, ArrayList<User>> roomInfo1 = this.hashMap.get(id).getKey();
        Integer capacity = this.getCapacityById(id);
        this.hashMap.replace(id, new Pair(roomInfo1, new Pair(capacity, booked)));
    }

    public ArrayList<User> getSpeakerById(int id){
        return this.hashMap.get(id).getKey().getValue();
    }

    public boolean updateSpeaker(int id, User u){
        ArrayList<User> speakers = this.getSpeakerById(id);
        Event e = this.getEventById(id);
        Pair<Integer, ArrayList<User>> roomInfo2 = this.hashMap.get(id).getValue();

        speakers.add(u);
        this.hashMap.replace(id, new Pair(new Pair(e, speakers), roomInfo2));
        return true;
    }

    public void updateEventCapacity(int id, int capacity){
        Pair<Event, ArrayList<User>> roomInfo1 = this.hashMap.get(id).getKey();
        ArrayList<User> attendee = this.getAttendeeById(id);
        this.hashMap.replace(id, new Pair(roomInfo1, new Pair(capacity, attendee)));

    }



    public void cancelEventById(int id){
        this.hashMap.remove(id);
    }
}
