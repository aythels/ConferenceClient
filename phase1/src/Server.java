import api.InitializeAPI;
import domain.usecases.EventManager;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;
import domain.usecases.Serializer;

public class Server {
    private InitializeAPI clientAPI;
    private MessageManager messageManager;
    private UserManager userManager;
    private EventManager eventManager;

    public Server () {
        MessageManager messageManager = new MessageManager();
        UserManager userManager = new UserManager();
        EventManager eventManager = new EventManager();

        this.clientAPI = new InitializeAPI(messageManager, userManager, eventManager);
    }

    public InitializeAPI getAPI(){
        return clientAPI;
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
