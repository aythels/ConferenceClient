package controllers;

import domain.entities.Event;
import domain.entities.User;
import domain.usecases.UserManager;

import java.util.List;

public class MessengerController {

    private LoginHelper loginHelper;
    private UserManager userManager;

    public MessengerController(UserManager userManager, LoginHelper loginHelper){
        this.loginHelper = loginHelper;
        this.userManager = userManager;
    }

    //user level permissions
    public List<String> getMessagebleUsers(String accessCode) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return null;
    }

    public List<String> getUserConvo(String accessCode, String otherUserID) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return null;
        User u = loginHelper.getUserByAccessCode(accessCode);
        User b = userManager.getUser(otherUserID);

        //need to implement more here
    }

    public void messageUser(String accessCode, String otherUserID, String message) {
        if (!loginHelper.isAuthorizedUser(accessCode)) return;
        User u = loginHelper.getUserByAccessCode(accessCode);
        User b = userManager.getUser(otherUserID);
    }



    //speaker level permissions
    public void messageUserArray(String accessCode, List<String> userIDArray, String message) {
        if (!loginHelper.isAuthorizedSpeaker(accessCode)) return;

        for (String userID : userIDArray) {
            messageUser(accessCode, userID, message);
        }
    }
}
