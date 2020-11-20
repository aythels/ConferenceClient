import adapters.Client;
import adapters.Server;

public class App {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
    }
}

