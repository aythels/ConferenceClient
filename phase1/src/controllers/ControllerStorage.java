package controllers;

import controllers.eventcontrollers.Controller;
import controllers.helpers.UserType;

import java.util.HashMap;

class ControllerStorage {
    private HashMap<UserType, Controller> storage;

    protected ControllerStorage() {
        this.storage = new HashMap<UserType, Controller>();

    }

    protected void addController(UserType userType, Controller controller) {
        this.storage.put(userType, controller);
    }

    protected Controller getController(UserType userType) {
        Controller searchedController = storage.get(userType);
        return searchedController;
    }
}
