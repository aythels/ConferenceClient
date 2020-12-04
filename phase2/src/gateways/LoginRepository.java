package gateways;

import core.usecases.ports.ILoginRepository;
import gateways.stores.LoginStore;

import java.io.*;

public class LoginRepository extends AbstractRepository implements ILoginRepository {

    private final File file;
    private final LoginStore store;

    public LoginRepository(String path) throws IOException {
        super(path);
        file = new File(path, "logins.ser");
        if (file.createNewFile()) {
            store = new LoginStore();
            put(file, store);
        } else {
            store = (LoginStore) get(file);
        }
    }

    @Override
    public boolean contains(String username) {
        return store.getLogins().containsKey(username);
    }

    @Override
    public void addLogin(String username, String password) {
        store.getLogins().put(username, password);
        put(file, store);
    }

    @Override
    public String getPassword(String username) {
        return store.getLogins().get(username);
    }
}
