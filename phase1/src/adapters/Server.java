package adapters;

import api.API;
import api.InitializeAPI;
import domain.usecases.EventManager;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;
import domain.usecases.Serializer;

public class Server {
    private API api;
    private MessageManager messageManager;
    private UserManager userManager;
    private EventManager eventManager;

    /**
     * A class enclosing all back-end functionality.
     */

    public Server () {
        this.messageManager = new MessageManager();
        this.userManager = new UserManager();
        this.eventManager = new EventManager();

        this.api = new InitializeAPI(this.messageManager, this.userManager, this.eventManager).getAPI();
    }

    public API getAPI() {
        return this.api;
    }


    /**
     * Load in saved use case classes.
     */

    public void Load() {
        Serializer s = new Serializer();

        this.userManager = s.deserializeUserManager();
        this.messageManager = s.deserializeMessageManager();
        this.eventManager = s.deserializeEventManager();

        this.api = new InitializeAPI(this.messageManager, this.userManager, this.eventManager).getAPI();
    }

    /**
     * Save the current state of all use case classes.
     */

    public void Save() {
        Serializer s = new Serializer();

        s.serializeUserManager(this.userManager);
        s.serializeEventManager(this.eventManager);
        s.serializeMessageManager(this.messageManager);
    }

}
