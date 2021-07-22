package gui.presenters;

import api.API;
import gui.helpers.ClientData;

public class LoginPresenter {
    private final API api;
    private final ClientData clientData;

    public LoginPresenter(API api, ClientData clientData) {
        this.api = api;
        this.clientData = clientData;
    }

    public boolean login(String username, String password) {
        String accessCode = api.call("login_controller", null, "login",
                username, password);

        if (accessCode != "null") {
            clientData.accessCode = accessCode;
            clientData.username = username;
            clientData.userDisplayName = api.call("user_controller", null,
                    "getUserName",
                    username);
            clientData.userType = api.call("user_controller", null,
                    "getUserType",
                    username);

            return true;
        }
        else return false;
    }
}
