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

    public Server () {
        this.messageManager = new MessageManager();
        this.userManager = new UserManager();
        this.eventManager = new EventManager();

        this.api = new InitializeAPI(this.messageManager, this.userManager, this.eventManager).getAPI();
    }

    public API getAPI(){
        return api;
    }

    public void Load() {
        Serializer s = new Serializer();

        this.userManager = s.deserializeUserManager();
        this.messageManager = s.deserializeMessageManager();
        this.eventManager = s.deserializeEventManager();
    }

    public void Save() {
        Serializer s = new Serializer();

        s.serializeUserManager(this.userManager);
        s.serializeEventManager(this.eventManager);
        s.serializeMessageManager(this.messageManager);
    }

}
