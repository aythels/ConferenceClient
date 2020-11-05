package domain.usecases;

import domain.entities.User;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class UserManager {

    private List<User> Users;

    public UserManager() {
        this.Users = new ArrayList<User>();
    }

    public void createUser(String name, String userType, String ID, String password) {
        User u = new User(name, userType, ID, password);
        this.Users.add(u);
    }

    public String getUserType(User u) {
        if (this.Users.contains(u)) {
            return u.getUserType();
        }
        return "No such user exists!";
    }

    public boolean passwordMatch(User u, String s) {
        if (u.getPassword() == s) {
            return true;
        }
        return false;
    }
}