package api.helpers;

import domain.entities.User;
import java.util.HashMap;
import java.util.UUID;

public class LoginHelper {
    private final HashMap<String, User> loggedUsers;

    /**
     * Map access codes to Users. Any entry in the hashmap is a user currently logged into the server.
     */

    public LoginHelper() {
        this.loggedUsers = new HashMap<>();
    }

    /**
     * Generate an UUID access code.
     * @return an access code string.
     */

    public String genAccessCode() {
        return UUID.randomUUID().toString();
    }

    /**
     * Map the specified User to the access code key. This user is now considered "logged in".
     * @param accessCode the hashmap key.
     * @param u the hashmap value.
     */

    public void validateAccessCode(String accessCode, User u) {
        loggedUsers.put(accessCode, u);
    }

    /**
     * Unmap the specified User to the access code key. This user is no longer "logged in".
     * @param accessCode the key entry to remove from the map.
     */

    public void invalidateAccessCode(String accessCode) {
        loggedUsers.remove(accessCode);
    }

    /**
     * Check if the specified access code key exists in the hashmap. Checks if the user is "logged in"
     * @param accessCode the hashmap key.
     * @return true if the key was found, false otherwise.
     */

    public boolean isValidAccessCode(String accessCode) {
        return loggedUsers.containsKey(accessCode); }

    /**
     * Get the mapped user by its access code key in the hashmap.
     * @param accessCode the hashmap key.
     * @return the mapped user if found, null otherwise.
     */

    public User getUserByAccessCode(String accessCode) {
        return loggedUsers.get(accessCode);
    }

    /**
     * Get the mapped user's UserType attribute by its access code key in the hashmap.
     * @param accessCode the hashmap key.
     * @return the mapped user's UserType attribute if found, null otherwise.
     */

    public UserType getUserTypeByAccessCode(String accessCode) {
        String userType = getUserByAccessCode(accessCode).getUserType();
        if (UserType.contains(userType)) return UserType.valueOf(userType);

        return null;
    }

}
