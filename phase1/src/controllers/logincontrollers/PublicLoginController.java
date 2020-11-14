package controllers.logincontrollers;

import controllers.LoginHelper;
import domain.entities.User;
import domain.usecases.UserManager;

class PublicLoginController extends LoginController {
    protected final UserManager userManager;
    protected final LoginHelper loginHelper;

    protected PublicLoginController(UserManager userManager, LoginHelper loginHelper) {
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
        User u = userManager.getUser(userID);

        if(u != null && userManager.passwordMatch(u, userPassword)) {
            String accessCode = loginHelper.genAccessCode();
            loginHelper.validateAccessCode(accessCode, u);
            return accessCode;
        }

        else return null;
    }

}
