package adapters;

import core.usecases.ports.ILoginRepository;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.HashMap;

public class LoginRepository implements ILoginRepository {

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
            put();
        } else {
            get();
        }
    }

    @Override
    public boolean contains(String username) {
        return logins.containsKey(username);
    }

    @Override
    public void addLogin(String username, String password) {
        logins.put(username, password);
        put();
    }

    @Override
    public String getPassword(String username) {
        return logins.get(username);
    }

    private void get() {
        try {
            FileInputStream fileIn = new FileInputStream(store);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            logins = (HashMap<String, String>) objIn.readObject();
            objIn.close();
            fileIn.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void put() {
        try {
            FileOutputStream fileOut = new FileOutputStream(store);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(logins);
            objOut.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
