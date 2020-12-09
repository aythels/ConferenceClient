package api;

import api.helpers.LoginHelper;
import api.helpers.UserType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

public class API {
    private final HashMap<String, ArrayList<Object>> controllerGroups;
    private final HashMap<Object, UserType> controllerUserType;
    private final LoginHelper loginHelper;
    private final APIHelper apiHelper;

    public API(LoginHelper loginHelper, APIHelper apiHelper) {
        this.controllerGroups = new HashMap<>();
        this.controllerUserType = new HashMap<>();
        this.loginHelper = loginHelper;
        this.apiHelper = apiHelper;
    }

    public void createGroup(String path) {
        this.controllerGroups.put(path, new ArrayList());
    }

    public void add(String path, Object controller) {
        this.add(path, controller, null);
    }

    public void add(String path, Object controller, UserType userType) {
        this.controllerGroups.get(path).add(controller);
        this.controllerUserType.put(controller, userType);
    }

    public ArrayList<Object> getControllerGroup(String path) {
        return this.controllerGroups.get(path);
    }

    public Object getController(ArrayList<Object> controllerGroup, UserType userType) {
        Object defaultController = null;
        for (Object controller : controllerGroup) {
            if (controllerUserType.get(controller) == userType) return controller;
            else if (controllerUserType.get(controller) == null) defaultController = controller;
        }

        return defaultController;
    }

    public String call(String path, String accessCode, String methodName, Object ... args) {
        ArrayList<Object> controllerGroup =getControllerGroup(path);
        if (controllerGroup == null) {
            System.out.println("Invalid path");
            return null;
        }

        UserType userType = loginHelper.isValidAccessCode(accessCode) ?
                loginHelper.getUserTypeByAccessCode(accessCode) : null;
        Object controller = getController(controllerGroup, userType);
        if (controller == null) {
            System.out.println("Could not get controller (invalid accessCode or non existent controller?)");
            return null;
        }

        Class[] parameterTypes = Stream.of(args).map(a -> a.getClass()).toArray(Class[]::new);
        Method method = this.apiHelper.getMethod(controller.getClass(), methodName, parameterTypes);

        if (method == null) {
            System.out.println("Could not get declared method " + "'" + methodName + "'" +
                    " with parameters of types " + Arrays.toString(parameterTypes) +
                    " in controller class " + "<" + controller.getClass().getSimpleName() + ">");
            return null;
        }

        return String.valueOf(this.apiHelper.callMethod(controller, method, args));

    }


}
