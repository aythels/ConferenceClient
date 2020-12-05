package api.controllers.usercontrollers;

import api.helpers.UserType;
import domain.entities.User;
import domain.usecases.UserManager;

public class PublicUserController {
    protected final UserManager userManager;

    /**
     * @param userManager Use case class responsible for handing everything related to events.
     */

    public PublicUserController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Creates a new user account as the specified account type
     * THIS IS A TEMPORARY METHOD THAT CAN BE USED TO QUICKLY CREATE DIFFERENT ACCOUNTS FOR TESTING!
     * @param userType    the type of user, as specified in UserType
     * @param name    the name of the user.
     * @param userID    the login ID that will be used to log in the user.
     * @param userPassword  the login password that will be used to log in the user.
     * @return  true if account creation was successful, false otherwise (for reasons such as if userID is already
     *          taken or invalid userType).
     */

    public boolean createAnyUserTEMPORARY(String userType, String name, String userID, String userPassword) {
        if (userManager.userExists(userID)) return false;
        if (!UserType.contains(userType)) return false;

        return userManager.createUser(name, userType, userID, userPassword);
    }

    /**
     * Creates a new user account as type "ATTENDEE"
     * @param name    the name of the user.
     * @param userID    the login ID that will be used to log in the user.
     * @param userPassword  the login password that will be used to log in the user.
     * @return  true if account creation was successful, false otherwise (for reasons such as if userID is already
     *          taken).
     */

    public boolean createUser(String name, String userID, String userPassword) {
        if (userManager.userExists(userID)) return false;
        return userManager.createUser(name, UserType.ATTENDEE.name(), userID, userPassword);
    }

    /**
     * Gets the name of an user by their userID.
     * @param userID    the login ID of the user to search for.
     * @return  the name of the user if userID points to an existing user, null otherwise.
     */

    public String getUserName(String userID) {
        if (!userManager.userExists(userID)) return null;

        User u = userManager.getUser(userID);
        return userManager.getName(u);
    }

    /**
     * Gets the type of an user by their userID.
     * @param userID    the login ID of the user to search for.
     * @return  the type of the user if userID points to an existing user, null otherwise.
     */

    public String getUserType(String userID) {
        if (!userManager.userExists(userID)) return null;

        User u = userManager.getUser(userID);
        return userManager.getUserType(u);
    }


}
