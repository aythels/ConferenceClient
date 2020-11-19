package controllers.logincontrollers;

import controllers.helpers.LoginHelper;
import domain.entities.User;
import domain.usecases.UserManager;

public class PublicLoginController extends LoginController {
    protected final UserManager userManager;
    protected final LoginHelper loginHelper;

    public PublicLoginController(UserManager userManager, LoginHelper loginHelper) {
        this.userManager = userManager;
        this.loginHelper = loginHelper;
    }

    /**
     * Logs in the specified user by generating a temporary access code that is required as a parameter to use certain
     * controller methods.
     * @param userID    the login ID of the user.
     * @param userPassword  the login password of the user.
     * @return  a unique access string if the login was successful or null otherwise.
     */

    public String login(String userID, String userPassword) {
        if (!userManager.userExists(userID)) return null;

        User u = userManager.getUser(userID);

        if(userManager.passwordMatch(u, userPassword)) {
            String accessCode = loginHelper.genAccessCode();
            loginHelper.validateAccessCode(accessCode, u);
            return accessCode;
        }

        return null;
    }

    /**
     * Logs out the specified user by invalidating their access code.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     */

    public void logout(String accessCode) {
        if (!loginHelper.isValidAccessCode(accessCode)) return;
        loginHelper.invalidateAccessCode(accessCode);
    }

}
