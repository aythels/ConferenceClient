package controllers;

import domain.entities.User;
import domain.usecases.UserManager;

public class UserController {
    private LoginHelper loginHelper;
    private UserManager userManager;

    public UserController(UserManager userManager, LoginHelper loginHelper){
        this.loginHelper = loginHelper;
        this.userManager = userManager;
    }
    //public permission level methods
    public String getUserName(String userID) {
        User u = userManager.getUser(userID);
        return userManager.getName(u);
    }

    public String getUserType(String userID) {
        User u = userManager.getUser(userID);
        return userManager.getUserType(u);
    }

    //user permission level methods
    public void setUserName(String accessCode, String newName) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return;
        userManager.setUsername(loginHelper.getUserByAccessCode(accessCode), newName);
    }

    public void setUserPassword(String accessCode, String newPassword) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return;
        userManager.setPassword(loginHelper.getUserByAccessCode(accessCode), newName);
    }

    //organizer permission level methods
    public void createUser(String accessCode, String name, String userType, String ID, String password) {
        if (!loginHelper.isAuthorizedOrganizer(accessCode)) return;
        userManager.createUser(name, userType, ID, password);
    }

}
