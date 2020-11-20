package api.controllers.usercontrollers;

import api.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.UserManager;

public class AttendeeUserController extends PublicUserController {
    private final LoginHelper loginHelper;

    public AttendeeUserController(UserManager userManager, LoginHelper loginHelper) {
        super(userManager);
        this.loginHelper = loginHelper;
    }

    /**
     * Change the name of the current client user.
     * @param accessCode    a code unique to the current client.
     * @param newName    the name to change to.
     * @return true if an attempt to change was made, false otherwise.
     */

    public boolean setUserName(String accessCode, String newName) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        userManager.setUsername(thisUser, newName);
        return true;
    }

    /**
     * Change the password of the current client user.
     * @param accessCode    a code unique to the current client.
     * @param newPassword    the password to change to.
     * @return true if an attempt to change was made, false otherwise.
     */

    public boolean setUserPassword(String accessCode, String newPassword) {
        if (!loginHelper.isValidAccessCode(accessCode)) return false;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        userManager.setPassword(thisUser, newPassword);
        return true;
    }

}
