package api.helpers;

import domain.entities.User;
import java.util.HashMap;
import java.util.UUID;

public class LoginHelper {
    private HashMap<String, User> loggedUsers;

    public LoginHelper() {
        this.loggedUsers = new HashMap<String, User>();
    }

    public String genAccessCode() {
        return UUID.randomUUID().toString();
    }

    public void validateAccessCode(String accessCode, User u) {
        loggedUsers.put(accessCode, u);
    }

    public void invalidateAccessCode(String accessCode) {
        loggedUsers.remove(accessCode);
    }

    public boolean isValidAccessCode(String accessCode) {
        return loggedUsers.containsKey(accessCode); }

    public User getUserByAccessCode(String accessCode) {
        return loggedUsers.get(accessCode);
    }

    public UserType getUserTypeByAccessCode(String accessCode) {
        String userType = getUserByAccessCode(accessCode).getUserType();
        if (UserType.contains(userType)) return UserType.valueOf(userType);

        return null;
    }

}
