package gui.presenters;

import api.API;
import gui.helpers.ClientData;

import java.util.*;
import java.util.stream.Collectors;

public class EventsPresenter {
    private final API api;
    private final ClientData clientData;

    public EventsPresenter(API api, ClientData clientData) {
        this.api = api;
        this.clientData = clientData;
    }

    private ArrayList<HashMap<String, String>> getEventDetails() {

        String[] eventIDs = api.call("event_controller", this.clientData.accessCode,
                "getAllEventIDs")
                .replaceAll("[\\[\\]\\s]", "").split(",");

        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        for (String eventID : eventIDs) {

            HashMap<String, String> map = new HashMap<>();

            map.put("eventID", eventID);
            map.put("speakerID", api.call("event_controller", null, "getEventSpeakerID", eventID));
            map.put("speakerName", api.call("event_controller", null, "getEventSpeakerName", eventID).replaceAll("[\\[\\]]", ""));
            map.put("eventName", api.call("event_controller", null, "getEventName", eventID));
            map.put("eventTime", api.call("event_controller", null, "getEventTime", eventID));
            map.put("eventDuration", api.call("event_controller", null, "getEventDuration", eventID));
            data.add(map);
        }

        return data;
    }

    public ArrayList<HashMap<String, String>> getEventDetailsByRegistered(String substring) {

        String[] registeredEventIDs = api.call("event_controller", this.clientData.accessCode,
                "getAllRegisteredEventIDs", this.clientData.accessCode)
                .replaceAll("[\\[\\]\\s]", "").split(",");
        ArrayList<HashMap<String, String>> data = getEventDetails();
        data.removeIf(s -> !Arrays.stream(registeredEventIDs).anyMatch(s.get("eventID")::equals));

        return getEventDetailsWithName(data, substring);
    }

    public ArrayList<HashMap<String, String>> getEventDetailsByName(String substring) {

        ArrayList<HashMap<String, String>> data = getEventDetails();
        Collections.sort(data, Comparator.comparing(a -> a.get("eventName")));

        return getEventDetailsWithName(data, substring);
    }

    public ArrayList<HashMap<String, String>> getEventDetailsByDate(String substring) {

        ArrayList<HashMap<String, String>> data = getEventDetails();
        Collections.sort(data, Comparator.comparing(a -> a.get("eventTime")));

        return getEventDetailsWithName(data, substring);
    }

    private ArrayList<HashMap<String, String>> getEventDetailsWithName(ArrayList<HashMap<String, String>> data, String substring) {

        data.removeIf(s -> !s.get("eventName").toLowerCase().contains(substring.toLowerCase()));

        return data;
    }

    public String getUserType(){
        return this.clientData.userType;
    }

    public String getUserName(){
        return this.clientData.username;
    }



    public boolean checkIfIsVIPEvent(String name){
        if (api.call("event_controller", null, "ifVIP", name) == "true"){
            return true;
        }
        return false;
    }

    public boolean checkIfIsSpeakerForEvent(String name){
        ArrayList<HashMap<String, String>> data = this.getEventDetails();
        for (HashMap<String, String> h: data){
            if(h.get("eventName") == name){
                if (h.get("speakerName").contains(clientData.username)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ifAttendeeRegisteredInEvent(String eventName){
        String[] registeredEventIDs = api.call("event_controller", this.clientData.accessCode,
                "getAllRegisteredEventIDs", this.clientData.accessCode)
                .replaceAll("[\\[\\]\\s]", "").split(",");
        for (String ID: registeredEventIDs){
            String name = api.call("event_controller", this.clientData.accessCode, "getEventNameByID", ID);
            if (eventName == name){
                return true;
            }
        }
        return false;
    }



}
