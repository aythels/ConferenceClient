package controllers;

import domain.entities.User;
import domain.usecases.UserManager;

public class LoginController {
    private LoginHelper loginHelper;
    private UserManager userManager;

    protected LoginController(UserManager userManager, LoginHelper loginHelper){
        this.loginHelper = loginHelper;
        this.userManager = userManager;
    }

    //public permission level methods

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

    /**
     * Logs out the specified user by invalidating their access code.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     */

    public void logout(String accessCode) {
        loginHelper.invalidateAccessCode(accessCode);
    }

}
