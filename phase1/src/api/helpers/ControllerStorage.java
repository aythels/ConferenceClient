package api.helpers;

import api.controllers.Controller;
import java.util.HashMap;

public class ControllerStorage {
    private final HashMap<UserType, Controller> allControllers;
    private Controller defaultController;

    /**
     * Map controllers to user types.
     */

    public ControllerStorage() {
        this.allControllers = new HashMap<>();
        this.defaultController = null;

        for (UserType userType : UserType.values()) {
            this.allControllers.put(userType, null);
        }

    }

    /**
     * Map the specified controller to the specified user type
     * @param userType the user type as specified in UserType enum class
     * @param controller the controller to map to the specified user type
     */

    public void addController(UserType userType, Controller controller) {
        this.allControllers.put(userType, controller);
    }

    /**
     * Set the default controller. The default controller is a controller not belonging to any user type.
     * @param controller the controller to be set as default
     */

    public void setDefaultController(Controller controller) {
        this.defaultController = controller;
    }

    /**
     * Get the controller mapped to the specified user type
     * @param userType the user type as specified in UserType enum class
     * @return the controller mapped to the specified user type, or null if no controller is found.
     */

    public Controller getController(UserType userType) {
        return allControllers.get(userType);
    }

    /**
     * Get the default controller
     * @return the default controller, or null if no controller is found.
     */

    public Controller getDefaultController() {
        return this.defaultController;
    }
}
