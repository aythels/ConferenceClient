package controllers.logincontrollers;

import controllers.LoginHelper;
import domain.usecases.UserManager;

class UserLoginController extends PublicLoginController {

    protected UserLoginController(UserManager userManager, LoginHelper loginHelper) {
        super(userManager, loginHelper);
    }

    /**
     * Logs out the specified user by invalidating their access code.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     */

    public void logout(String accessCode) {
        loginHelper.invalidateAccessCode(accessCode);
    }

}
