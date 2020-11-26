package core.usecases.ports;

public interface ILoginRepository {

    boolean contains(String username);

    void addLogin(String username, String password);

    String getPassword(String username);
}
