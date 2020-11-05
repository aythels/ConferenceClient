package domain.usecases;

import domain.entities.User;

import java.util.List;
import java.util.ArrayList;

class UserManager {

    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<User>();
    }

    public void createUser(String name, String userType, String ID, String password) {
        User u = new User(name, userType, ID, password);
        this.users.add(u);
    }

    public User getUser(String username) {
        for (User u : users) {
            if ((u.getUsername()).equals(username)) {
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