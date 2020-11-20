package api;

import api.controllers.Controller;
import api.helpers.ControllerStorage;
import api.helpers.LoginHelper;

public class API {
    private ControllerStorage allLoginControllers;
    private ControllerStorage allMessengerControllers;
    private ControllerStorage allUserControllers;
    private ControllerStorage allEventControllers;

    private LoginHelper loginHelper;

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

    //PUBLIC CONTROLLERS
    public Controller getLoginAPI() {
        return allLoginControllers.getDefaultController();
    }

    public Controller getUserAPI() {
        return allUserControllers.getDefaultController();
    }

    public Controller getMessengerAPI() {
        return allMessengerControllers.getDefaultController();
    }

    public Controller getEventAPI() {
        return allEventControllers.getDefaultController();
    }

    //PERMISSION BASED CONTROLLERS
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
