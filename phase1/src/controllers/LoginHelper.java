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

    protected void validateAccessCode(String accessCode, User u) {
        loggedUsers.put(accessCode, u);
    }

    protected void invalidateAccessCode(String accessCode) {
        loggedUsers.remove(accessCode);
    }

    protected User getUserByAccessCode(String accessCode) {
        return loggedUsers.get(accessCode);
    }

    protected boolean isValidAccessCode(String accessCode) {
        return loggedUsers.containsKey(accessCode); }

}
