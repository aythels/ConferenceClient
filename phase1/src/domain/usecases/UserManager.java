package domain.usecases;

import domain.entities.User;

import java.util.List;
import java.util.ArrayList;

public class UserManager {

    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<User>();
    }

    public boolean createUser(String name, String userType, String ID, String password) {
        if (getUser(ID) != null) return false;

        User u = new User(name, userType, ID, password);
        this.users.add(u);
        return true;
    }

    public boolean userExists(String userID) {
        for (User u : users) {
            if ((u.getID()).equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String userID) {
        for (User u : users) {
            if ((u.getID()).equals(userID)) {
                return u;
            }
        }
        return null;
    }

    public String getName(User u) {
        return u.getName();
    }

    public String getUserType(User u) {
        return u.getUserType();
    }

    public String getID(User u) {
        return u.getID();
    }

    public String getPassword(User u) {
        return u.getPassword();
    }

    public void setName(User u, String name) {
        u.setName(name);
    }

    public void setUserType(User u, String type) {
        u.setUserType(type);
    }

    public void setUsername(User u, String username) {
        u.setUsername(username);
    }

    public void setPassword(User u, String password) {
        u.setPassword(password);
    }

    public boolean passwordMatch(User u, String s) {
        return (u.getPassword()).equals(s);
    }
}