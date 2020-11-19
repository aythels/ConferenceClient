import controllers.MasterController;
import domain.usecases.EventManager;
import domain.usecases.MessageManager;
import domain.usecases.UserManager;

public class Server {
    private MasterController clientAPI;

    public Server () {
        MessageManager messageManager = null;
        UserManager userManager = null;
        EventManager eventManager = null;

        this.clientAPI = new MasterController(messageManager, userManager, eventManager);
    }

    public MasterController getAPI(){
        return clientAPI;
    }

}
