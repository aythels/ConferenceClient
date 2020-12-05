package api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class APIHelper {

    protected Method getMethod (Class c, String methodName, Object ... args) {
        for (Method method : c.getDeclaredMethods())
            if (method.getName().equals(methodName))
                if (verifyArguments(method.getParameterTypes(), args)) return method;

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

    protected boolean verifyArguments (Class[] parameterTypes, Object[] args) {
        if (parameterTypes.length != args.length) return false;

        for (int i = 0; i < parameterTypes.length; i++)
            if (parameterTypes[i] != args[i].getClass()) return false;

        return true;
    }

}
