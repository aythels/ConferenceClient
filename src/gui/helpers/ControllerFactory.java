package gui.helpers;

import javafx.util.Callback;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ControllerFactory implements Callback<Class<?>, Object> {
    private final Object[] injectedObjects;
    private final Class[] injectedClasses;

    public ControllerFactory(Object ... dependencies) {
        this.injectedObjects = new Object[dependencies.length];
        this.injectedClasses = new Class[dependencies.length];

        for (int i = 0; i < dependencies.length; i++) {
            this.injectedObjects[i] = dependencies[i];
            this.injectedClasses[i] = dependencies[i].getClass();
        }

    }

    @Override
    public Object call(Class<?> type) {
        //https://stackoverflow.com/questions/39806497/java-alternative-to-fxml-injection-of-controllers
        //https://stackoverflow.com/questions/20440839/cant-invoke-method-with-varargs-parameters-with-reflection-nosuchmethodexcept
        /*
            if (type == LoginViewController.class) return new LoginViewController(this.pageIndex, this.clientData);
            if (type == HomeViewController.class) return new HomeViewController(this.pageIndex, this.clientData);
            return null ;
        */
        /*
            Constructor<?> constructor = type.getConstructor(PageIndex.class, ClientData.class);
            return constructor.newInstance(pageIndex, clientData);
         */

        Constructor<?> constructor = null;
        try {
            constructor = type.getConstructor(this.injectedClasses);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            return constructor.newInstance(this.injectedObjects);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

}
