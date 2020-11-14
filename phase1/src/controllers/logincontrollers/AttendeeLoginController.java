package controllers.logincontrollers;

import controllers.LoginHelper;
import domain.usecases.UserManager;

class AttendeeLoginController extends PublicLoginController {

    protected AttendeeLoginController(UserManager userManager, LoginHelper loginHelper) {
        super(userManager, loginHelper);
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
