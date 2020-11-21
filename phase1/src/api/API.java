package api;

import api.controllers.Controller;
import api.helpers.ControllerStorage;
import api.helpers.LoginHelper;

public class API {
    private final ControllerStorage allLoginControllers;
    private final ControllerStorage allMessengerControllers;
    private final ControllerStorage allUserControllers;
    private final ControllerStorage allEventControllers;

    private final LoginHelper loginHelper;

    /**
     * A class containing all the methods for fetching different controllers. Intended to be called by client.
     * @param loginHelper keeps track of logged in users. Used to verify access code parameters.
     * @param allLoginControllers contains controllers related to logging in, mapped to each user type.
     * @param allMessengerControllers contains controllers related to messaging, mapped to each user type.
     * @param allUserControllers contains controllers related to users, mapped to each user type.
     * @param allEventControllers contains controllers related to events, mapped to each user type.
     */

    public API(LoginHelper loginHelper,
               ControllerStorage allLoginControllers,
               ControllerStorage allMessengerControllers,
               ControllerStorage allUserControllers,
               ControllerStorage allEventControllers) {

        this.loginHelper = loginHelper;

        this.allLoginControllers = allLoginControllers;
        this.allMessengerControllers = allMessengerControllers;
        this.allUserControllers = allUserControllers;
        this.allEventControllers = allEventControllers;

    }

    /**
     * The following methods are used to fetch the specified public controller. A public controller is a controller
     * that does not need an access code parameter, hence does not require the client user to be logged in.
     * @return the specified public controller, null if unavailable.
     */

    public Controller getLoginAPI() {
        return allLoginControllers.getDefaultController();
    }

    public Controller getUserAPI() {
        return allUserControllers.getDefaultController();
    }

    public Controller getMessengerAPI() {
        return allMessengerControllers.getDefaultController();
    }

    public Controller allEventControllers() {
        return allEventControllers.getDefaultController();
    }

    /**
     * The following methods are used to fetch the specified private controller in its respective category. A private
     * controller is a controller that requires an access code to obtain and can vary in the amount of methods they
     * contain depending on what type of user the access code is associated with. Clients are required to first call
     * getLoginAPI() to get the controller responsible for logging in to obtain this access code.
     * @param accessCode the access code.
     * @return a private controller belonging to the specified category if available, null if unavailable or invalid
     * access code.
     */

    public Controller getUserAPI(String accessCode){
        if (!loginHelper.isValidAccessCode(accessCode)) return null;

        return allUserControllers.getController(loginHelper.getUserTypeByAccessCode(accessCode));
    }

    public Controller getMessengerAPI(String accessCode) {
        if (!loginHelper.isValidAccessCode(accessCode)) return null;

        return allMessengerControllers.getController(loginHelper.getUserTypeByAccessCode(accessCode));
    }

    public Controller getEventAPI(String accessCode) {
        if (!loginHelper.isValidAccessCode(accessCode)) return null;

        return allEventControllers.getController(loginHelper.getUserTypeByAccessCode(accessCode));
    }

}
