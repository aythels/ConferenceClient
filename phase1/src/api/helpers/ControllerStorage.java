package api.helpers;

import api.controllers.Controller;
import java.util.HashMap;

public class ControllerStorage {
    private HashMap<UserType, Controller> allControllers;
    private Controller defaultController;

    public ControllerStorage() {
        this.allControllers = new HashMap<UserType, Controller>();
        this.defaultController = null;

        for (UserType userType : UserType.values()) {
            this.allControllers.put(userType, null);
        }

    }

    public void addController(UserType userType, Controller controller) {
        this.allControllers.put(userType, controller);
    }

    public void setDefaultController(Controller controller) {
        this.defaultController = controller;
    }

    public Controller getController(UserType userType) {
        Controller controller = allControllers.get(userType);

        return controller;
    }

    public Controller getDefaultController() {
        return this.defaultController;
    }
}
