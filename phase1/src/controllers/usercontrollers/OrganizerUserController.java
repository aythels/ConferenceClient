package controllers.usercontrollers;

import controllers.LoginHelper;
import domain.usecases.UserManager;

class OrganizerUserController extends AttendeeUserController {

    protected OrganizerUserController(UserManager userManager, LoginHelper loginHelper) {
        super(userManager, loginHelper);
    }

    /**
     * Creates a new user account as the specified account type
     * @param userType    the type of user ("ATTENDEE"/"SPEAKER"/"ORGANIZER").
     * @param name    the name of the user.
     * @param userID    the login ID that will be used to log in the user.
     * @param userPassword  the login password that will be used to log in the user.
     * @return  true if account creation was successful, false otherwise (for reasons such as if userID is already
     *          taken).
     */

    public boolean createAnyUser(String userType, String name, String userID, String userPassword) {
        return userManager.createUser(name, userType, userID, userPassword);
    }

}
