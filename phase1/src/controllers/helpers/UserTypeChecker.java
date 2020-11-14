package controllers.helpers;

import domain.entities.User;

public class UserTypeChecker {
    private final LoginHelper loginHelper;

    protected UserTypeChecker(LoginHelper loginHelper){
        this.loginHelper = loginHelper;
    }

    protected boolean isAuthorizedUser(String accessCode) {
        if (loginHelper.isValidAccessCode(accessCode)) return true;

        return false;
    }

    protected boolean isAuthorizedSpeaker(String accessCode){
        if (!loginHelper.isValidAccessCode(accessCode)) return false;

        User u = loginHelper.getUserByAccessCode(accessCode);
        if (u.getUserType() == "SPEAKER" || u.getUserType() == "ORGANIZER") return true;

        return false;

    }

    protected boolean isAuthorizedOrganizer(String accessCode){
        if (!loginHelper.isValidAccessCode(accessCode)) return false;

        User u = loginHelper.getUserByAccessCode(accessCode);
        if (u.getUserType() == "ORGANIZER") return true;

        return false;
    }
}
