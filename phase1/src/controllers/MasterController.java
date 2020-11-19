package controllers;

import controllers.eventcontrollers.*;
import controllers.helpers.LoginHelper;
import controllers.helpers.UserType;
import controllers.logincontrollers.LoginController;
import controllers.logincontrollers.PublicLoginController;
import controllers.messengercontrollers.AttendeeMessengerController;
import controllers.messengercontrollers.MessengerController;
import controllers.messengercontrollers.SpeakerMessengerController;
import controllers.usercontrollers.AttendeeUserController;
import controllers.usercontrollers.OrganizerUserController;
import controllers.usercontrollers.PublicUserController;
import controllers.usercontrollers.UserController;
import domain.usecases.EventManager;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

public class MasterController {
    private ControllerStorage allLoginControllers;
    private ControllerStorage allMessengerControllers;
    private ControllerStorage allUserControllers;
    private ControllerStorage allEventControllers;

    private LoginHelper loginHelper;

    public MasterController(MessageManager messageManager, UserManager userManager, EventManager eventManager){
        //initialize
        this.allLoginControllers = new ControllerStorage();
        this.allMessengerControllers = new ControllerStorage();
        this.allUserControllers = new ControllerStorage();
        this.allEventControllers = new ControllerStorage();

        this.loginHelper = new LoginHelper();

        //LOGIN CONTROLLERS
        Controller _public = new PublicLoginController(userManager, loginHelper);
        Controller _attendee = null;
        Controller _speaker = null;
        Controller _organizer = null;
        allLoginControllers.addController(UserType._PUBLIC, _public);
        allLoginControllers.addController(UserType.ATTENDEE, _public);
        allLoginControllers.addController(UserType.SPEAKER, _public);
        allLoginControllers.addController(UserType.ORGANIZER, _public);

        //MESSENGER CONTROLLERS
        _public = null;
        _attendee = new AttendeeMessengerController(messageManager, userManager, loginHelper);
        _speaker = new SpeakerMessengerController(messageManager, userManager, loginHelper);
        _organizer = null;
        allMessengerControllers.addController(UserType._PUBLIC, null);
        allMessengerControllers.addController(UserType.ATTENDEE, _attendee);
        allMessengerControllers.addController(UserType.SPEAKER, _speaker);
        allMessengerControllers.addController(UserType.ORGANIZER, _speaker);

        //USER CONTROLLERS
        _public = new PublicUserController(userManager);
        _attendee = new AttendeeUserController(userManager, loginHelper);
        _speaker = null;
        _organizer = new OrganizerUserController(userManager, loginHelper);
        allUserControllers.addController(UserType._PUBLIC, _public);
        allUserControllers.addController(UserType.ATTENDEE, _attendee);
        allUserControllers.addController(UserType.SPEAKER, _attendee);
        allUserControllers.addController(UserType.ORGANIZER, _organizer);

        //EVENT CONTROLLERS
        _public = new PublicEventController(eventManager, userManager);
        _attendee = new AttendeeEventController(eventManager, userManager, loginHelper);
        _speaker = new SpeakerEventController(eventManager, userManager, loginHelper);
        _organizer = new OrganizerEventController(eventManager, userManager, loginHelper);
        allEventControllers.addController(UserType._PUBLIC, _public);
        allEventControllers.addController(UserType.ATTENDEE, _attendee);
        allEventControllers.addController(UserType.SPEAKER, _speaker);
        allEventControllers.addController(UserType.ORGANIZER, _organizer);

    }

    public LoginController getLoginAPI() {
        return (LoginController) allLoginControllers.getController(UserType._PUBLIC);
    }

    public UserController getUserAPI(String accessCode){
        UserType userType = getUserType(accessCode);
        if (userType == null) return  null;

        return (UserController) allUserControllers.getController(userType);
    }

    public MessengerController getMessengerAPI(String accessCode) {
        UserType userType = getUserType(accessCode);
        if (userType == null) return  null;

        return (MessengerController) allMessengerControllers.getController(userType);
    }

    public EventController getEventAPI(String accessCode) {
        UserType userType = getUserType(accessCode);
        if (userType == null) return  null;

        return (EventController) allEventControllers.getController(userType);
    }

    protected UserType getUserType(String accessCode) {
        if (loginHelper.isValidAccessCode(accessCode)) {
            String userType = loginHelper.getUserByAccessCode(accessCode).getUserType();
            if (UserType.contains(userType)) return UserType.valueOf(userType);
        }

        return null;
    }
}
