package gateways;

import core.usecases.ports.ILoginRepository;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;

public class LoginRepository extends AbstractRepository implements ILoginRepository {

    private final File store;
    private final Map<String, String> logins;

    @SuppressWarnings("unchecked")
    public LoginRepository(String path) throws IOException {
        super(path);
        store = new File(path, "logins.ser");
        if (store.createNewFile()) {
            logins = new HashMap<>();
            put(store, logins);
        } else {
            logins = (Map<String, String>) get(store);
        }
    }

    @Override
    public boolean contains(String username) {
        return logins.containsKey(username);
    }

    @Override
    public void addLogin(String username, String password) {
        logins.put(username, password);
        put(store, logins);
    }

    @Override
    public String getPassword(String username) {
        return logins.get(username);
    }
}
