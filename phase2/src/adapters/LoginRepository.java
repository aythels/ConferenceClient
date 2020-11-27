package adapters;

import core.usecases.ports.ILoginRepository;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Optional;

public class LoginRepository extends AbstractRepository implements ILoginRepository {

    private final File store;
    private HashMap<String, String> logins;

    public LoginRepository(String path) throws InvalidPathException, IOException {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            throw new InvalidPathException(path, "Invalid path");
        }
        store = new File(dir.getAbsolutePath() + "/logins.ser");
        if (!store.exists()) {
            store.createNewFile();
            logins = new HashMap<>();
            put(store, logins);
        } else {
            logins = (HashMap<String, String>) get(store).orElseThrow(IOException::new);
        }
    }

    @Override
    public boolean contains(String username) {
        return logins.containsKey(username);
    }

    @Override
    public void addLogin(String username, String password) {
        logins.put(username, password);
        try {
            put(store, logins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPassword(String username) {
        return logins.get(username);
    }
}
