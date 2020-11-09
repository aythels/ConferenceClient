package controllers;

import domain.entities.User;

import java.util.HashMap;
import java.util.UUID;

public class LoginHelper {
    private HashMap<String, User> loggedUsers;

    public LoginHelper() {
        this.loggedUsers = new HashMap<String, User>();
    }

    protected String genAccessCode() {
        return UUID.randomUUID().toString();
    }

    protected void validateUserAccess(String accessCode, User u) {
        loggedUsers.put(accessCode, u);
    }

    protected void invalidateUserAccess(String accessCode) {
        loggedUsers.remove(accessCode);
    }

    protected User getUserByAccessCode(String accessCode) {
        return loggedUsers.get(accessCode);
    }

    protected boolean isValidAccessCode(String accessCode) { return loggedUsers.containsKey(accessCode); }

    protected boolean isAuthorizedUser(String accessCode) {
        if (isValidAccessCode(accessCode)) return true;

        return false;
    }

    protected boolean isAuthorizedSpeaker(String accessCode){
        if (isValidAccessCode(accessCode)) {
            User u = getUserByAccessCode(accessCode);
            if (u.getUserType() == "SPEAKER" || u.getUserType() == "ORGANIZER") return true;
        }

        return false;

    }

    protected boolean isAuthorizedOrganizer(String accessCode){
        if (isValidAccessCode(accessCode)) {
            User u = getUserByAccessCode(accessCode);
            if (u.getUserType() == "ORGANIZER") return true;
        }

        return false;
    }
}
