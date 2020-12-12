package gui.presenters;

import api.API;
import gui.helpers.ClientData;

public class SettingsPresenter {
    private final API api;
    private final ClientData clientData;

    public SettingsPresenter(API api, ClientData clientData) {
        this.api = api;
        this.clientData = clientData;
    }

    public String getUserName() {
        return this.clientData.username;
    }

    public String getUserType() {
        return this.clientData.userType;
    }

    public String getUserDisplayName() {
        return this.clientData.userDisplayName;
    }

    public void changeDisplayName(String newName) {
        this.api.call("user_controller", clientData.accessCode,
                "setUserName",
                this.clientData.accessCode, newName);
    }

    public void changePassword(String newPassword) {
        this.api.call("user_controller", clientData.accessCode,
                "setUserPassword",
                this.clientData.accessCode, newPassword);
    }

    public boolean createAccount(String username, String password, String usertype) {
        return Boolean.parseBoolean(
                api.call("user_controller", clientData.accessCode, "createAnyUser",
                usertype, "user", username, password)
        );
    }

    public boolean isUserOrganizer() {
        if (this.clientData.userType == "ORGANIZER") return true;
        else return false;
    }
}
