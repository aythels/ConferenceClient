package gateways.stores;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginStore implements Serializable {

    private final Map<String, String> logins;

    public LoginStore() {
        logins = new HashMap<>();
    }

    public Map<String, String> getLogins() {
        return logins;
    }
}
