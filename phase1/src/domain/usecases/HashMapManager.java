package domain.usecases;

import domain.entities.Event;
import domain.entities.User;
import javafx.util.Pair;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class HashMapManager {
    private HashMap<Integer, Pair<Pair<Event, ArrayList<User>>, ArrayList<User>>> hashMap;

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

    public void addEvent(int eventId, int eventDuration, int eventTime, Event e){
        this.hashMap.put(eventId, new Pair(new Pair(e, new ArrayList<User>()), new ArrayList<User>()));
    }

    public ArrayList<User> getAttendeeById(int id){
        return this.hashMap.get(id).getValue();
    }


    public void updateAttendee(int id, ArrayList<User> booked){
        Pair<Event, ArrayList<User>> e = this.hashMap.get(id).getKey();
        this.hashMap.replace(id, new Pair(e, booked));
    }

    public ArrayList<User> getSpeakerById(int id){
        return this.hashMap.get(id).getKey().getValue();
    }

    public boolean updateSpeaker(int id, User u){
        ArrayList<User> speakers = this.getSpeakerById(id);
        Event e = this.getEventbyId(id);
        ArrayList<User> attendees = this.getAttendeeById(id);
        if (speakers.size() > 0){
            return false;
        }
        speakers.add(u);
        this.hashMap.replace(id, new Pair(new Pair(e, speakers), attendees));
        return true;
    }



    public void cancelEventById(int id){
        this.hashMap.remove(id);
    }
}
