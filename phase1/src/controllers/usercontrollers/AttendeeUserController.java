package controllers.usercontrollers;

import controllers.LoginHelper;
import domain.entities.User;
import domain.usecases.UserManager;

class AttendeeUserController extends PublicUserController {
    private final LoginHelper loginHelper;

    protected AttendeeUserController(UserManager userManager, LoginHelper loginHelper) {
        super(userManager);
        this.loginHelper = loginHelper;
    }

    /**
     * Change the name of the current client user.
     * @param accessCode    a code unique to the current client
     * @param newName    the name to change to.
     */

    public void setUserName(String accessCode, String newName) {
        if (!loginHelper.isValidAccessCode(accessCode)) return;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        userManager.setUsername(thisUser, newName);
    }

    /**
     * Change the password of the current client user.
     * @param accessCode    a code unique to the current client
     * @param newPassword    the password to change to.
     */

    public void setUserPassword(String accessCode, String newPassword) {
        if (!loginHelper.isValidAccessCode(accessCode)) return;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        userManager.setPassword(thisUser, newPassword);
    }

}
