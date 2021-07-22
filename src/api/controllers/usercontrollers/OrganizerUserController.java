package api.controllers.usercontrollers;

import api.helpers.LoginHelper;
import api.helpers.UserType;
import domain.usecases.UserManager;

public class OrganizerUserController extends AttendeeUserController {

    /**
     * @param userManager Use case class responsible for handing everything related to events.
     * @param loginHelper Helper class that keeps track of logged in users.
     */

    public OrganizerUserController(UserManager userManager, LoginHelper loginHelper) {
        super(userManager, loginHelper);
    }

    /**
     * Creates a new user account as the specified account type
     * @param userType    the type of user, as specified in UserType
     * @param name    the name of the user.
     * @param userID    the login ID that will be used to log in the user.
     * @param userPassword  the login password that will be used to log in the user.
     * @return  true if account creation was successful, false otherwise (for reasons such as if userID is already
     *          taken or invalid userType).
     */

    public boolean createAnyUser(String userType, String name, String userID, String userPassword) {
        if (userManager.userExists(userID)) return false;
        if (!UserType.contains(userType)) return false;

        return userManager.createUser(name, userType, userID, userPassword);
    }

}
