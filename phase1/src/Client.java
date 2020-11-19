import controllers.MasterController;

public class Client {
    private MasterController serverAPI;
    public Client(MasterController serverAPI){
        this.serverAPI = serverAPI;
    }
}
