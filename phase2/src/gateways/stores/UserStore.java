package gateways.stores;

import core.entities.User;
import core.entities.UserType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStore implements Serializable {

    private final Map<String, User> usersByUsername;
    private final Map<UserType, List<User>> usersByType;

    public UserStore() {
        usersByUsername = new HashMap<>();
        usersByType = new HashMap<>();
    }

    public Map<String, User> getUsersByUsername() {
        return usersByUsername;
    }

    public Map<UserType, List<User>> getUsersByType() {
        return usersByType;
    }
}
