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
        return this.clientData.getUserName();
    }

    public String getUserType() {
        return
                this.api.call("user_controller", clientData.getAccessCode(),
                "getUserType",
                this.clientData.getUserName());
    }

    public void changeDisplayName(String newName) {
        this.api.call("user_controller", clientData.getAccessCode(),
                "setUserName",
                this.clientData.getAccessCode(), newName);
    }

    public void changePassword(String newPassword) {
        this.api.call("user_controller", clientData.getAccessCode(),
                "setUserPassword",
                this.clientData.getAccessCode(), newPassword);
    }

    public boolean createAccount(String username, String password, String usertype) {
        return Boolean.parseBoolean(
                api.call("user_controller", clientData.getAccessCode(), "createAnyUser",
                usertype, "user", username, password)
        );
    }

}
