package domain.usecases;

import domain.entities.User;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class UserManager implements Serializable {

    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    /**
     * Creates a new User
     * @param name Name of User
     * @param userType Type of User
     * @param ID ID of User
     * @param password Password of User
     * @return True if a User with the above parameters does not exist
     */
    public boolean createUser(String name, String userType, String ID, String password) {
        if (getUser(ID) != null) return false;

        User u = new User(name, userType, ID, password);
        this.users.add(u);
        return true;
    }

    /**
     * Determines whether a User exists based off ID
     * @param userID ID of User
     * @return True if User exists
     */
    public boolean userExists(String userID) {
        for (User u : users) {
            if ((u.getID()).equals(userID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a specific User based off ID
     * @param userID ID of desired User
     * @return The User whose ID matches userID
     */
    public User getUser(String userID) {
        for (User u : users) {
            if ((u.getID()).equals(userID)) {
                return u;
            }
        }
        return null;
    }

    /**
     * @param u User
     * @return Name of User
     */
    public String getName(User u) {
        return u.getName();
    }

    /**
     * @param u User
     * @return Type of User
     */
    public String getUserType(User u) {
        return u.getUserType();
    }

    /**
     * @param u User
     * @return ID of User
     */
    public String getID(User u) {
        return u.getID();
    }

    /**
     * @param u User
     * @return Password of User
     */
    public String getPassword(User u) {
        return u.getPassword();
    }

    /**
     * @param u User
     * @param name Name to set for User
     */
    public void setName(User u, String name) {
        u.setName(name);
    }

    /**
     * @param u User
     * @param type Type of User to set for User
     */
    public void setUserType(User u, String type) {
        u.setUserType(type);
    }

    /**
     * @param u User
     * @param username Username to set for User
     */
    public void setUsername(User u, String username) {
        u.setUsername(username);
    }

    /**
     * @param u User
     * @param password Password to set for User
     */
    public void setPassword(User u, String password) {
        u.setPassword(password);
    }

    /**
     * @param u User
     * @param s Password to match with
     * @return True if s matches the User's password
     */
    public boolean passwordMatch(User u, String s) {
        return (u.getPassword()).equals(s);
    }
}