package controllers;

import domain.entities.User;
import domain.usecases.UserManager;

public class UserController {
    private LoginHelper loginHelper;
    private UserTypeChecker userTypeChecker;
    private UserManager userManager;

    public UserController(UserManager userManager, LoginHelper loginHelper, UserTypeChecker userTypeChecker){
        this.loginHelper = loginHelper;
        this.userManager = userManager;
        this.userTypeChecker = userTypeChecker;
    }

    //public permission level methods

    /**
     * Creates a new user account as type "ATTENDEE"
     * @param name    the name of the user.
     * @param userID    the login ID that will be used to log in the user.
     * @param userPassword  the login password that will be used to log in the user.
     * @return  true if account creation was successful, false otherwise (for reasons such as if userID is already
     *          taken).
     */

    public boolean createUser(String name, String userID, String userPassword) {
        return userManager.createUser(name, "ATTENDEE", userID, userPassword);
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

    //user permission level methods

    /**
     * Change the name of the current client user.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @param newName    the name to change to.
     */

    public void setUserName(String accessCode, String newName) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        userManager.setUsername(thisUser, newName);
    }

    /**
     * Change the password of the current client user.
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @param newPassword    the password to change to.
     */

    public void setUserPassword(String accessCode, String newPassword) {
        if (!userTypeChecker.isAuthorizedUser(accessCode)) return;

        User thisUser = loginHelper.getUserByAccessCode(accessCode);
        userManager.setPassword(thisUser, newPassword);
    }

    //organizer permission level methods

    /**
     * Creates a new user account as the specified account type
     * @param accessCode    a code that permits the caller to use controller methods restricted to their user type.
     * @param userType    the type of user ("ATTENDEE"/"SPEAKER"/"ORGANIZER").
     * @param name    the name of the user.
     * @param userID    the login ID that will be used to log in the user.
     * @param userPassword  the login password that will be used to log in the user.
     * @return  true if account creation was successful, false otherwise (for reasons such as if userID is already
     *          taken).
     */

    public boolean createAnyUser(String accessCode, String userType, String name, String userID, String userPassword) {
        if (!userTypeChecker.isAuthorizedOrganizer(accessCode)) return false;

        return userManager.createUser(name, userType, userID, userPassword);
    }

}
