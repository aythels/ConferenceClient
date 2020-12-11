package api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class APIHelper {

    protected Method getMethod (Class c, String methodName, Class[] parameterTypes) {

        try {
            return c.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            //theres no actual error here, the client just tried to get a invalid method
        }

        return null;
    }

    protected Object callMethod (Object object, Method method, Object[] args) {
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
