package controllers;

import domain.entities.User;
import domain.usecases.UserManager;

import java.util.HashMap;
import java.util.UUID;

public class LoginController {
    private LoginHelper loginHelper;
    private UserManager userManager;

    public LoginController(UserManager userManager, LoginHelper loginHelper){
        this.loginHelper = loginHelper;
        this.userManager = userManager;
    }

    //public permission level methods
    public String login(String userID, String password) {
        User u = userManager.getUser(userID);

        if (u && userManager.passwordMatch(u, password)) {
            String accessCode = loginHelper.genAccessCode();
            loginHelper.validateUserAccess(accessCode, u);
            return accessCode;
        }

        else return null;
    }

    //user permission level methods
    public void logout(String accessCode) {
        loginHelper.invalidateUserAccess(accessCode);
    }
}
