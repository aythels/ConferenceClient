package controllers.eventcontrollers;

import controllers.LoginHelper;
import domain.entities.Event;
import domain.entities.User;
import domain.usecases.EventManager;

import java.util.ArrayList;
import java.util.List;

class SpeakerEventController extends UserEventController{
    protected SpeakerEventController(EventManager eventManager, LoginHelper loginhelper) {
        super(eventManager, loginhelper);
    }

    public List<String> getAllInvolvedEventIDs(String accessCode) {
        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        List<String> allInvolvedEventIDs = new ArrayList<String>();
        List<Event> allEvents = eventManager.getAllEvents();

        //for (Event e : allEvents) if(eventManager.getEventSpeakerID(event));

        return allInvolvedEventIDs;
    }

    public List<String> getEventRegisteredUsers(String accessCode, String eventID) {
        User thisUser = loginHelper.getUserByAccessCode(accessCode);

        List<String> allUserIDs = new ArrayList<String>();
        //List<User> allUsers = eventManager.getAllRegisteredUsersByEvent();

        //for (User u : allUsers) allUserIDs.add(u.getEventId());

        return allUserIDs;
    }
}
