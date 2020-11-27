package adapters;

import core.entities.User;
import core.entities.UserType;
import core.usecases.ports.IUserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository extends AbstractRepository implements IUserRepository {

    private final File usersStore;
    private final File usersByTypeStore;
    private final HashMap<String, User> users;
    private final HashMap<UserType, List<User>> usersByType;

    public UserRepository(String path) throws IOException {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            throw new InvalidPathException(path, "Invalid path");
        }
        usersStore = new File(path + "/users.ser");
        usersByTypeStore = new File(path + "/usersByType.ser");
        if (!usersStore.exists()) {
            usersStore.createNewFile();
            users = new HashMap<>();
            put(usersStore, users);
        } else {
            users = (HashMap<String, User>) get(usersStore).orElseThrow(IOException::new);
        }
        if (!usersByTypeStore.exists()) {
            usersByTypeStore.createNewFile();
            usersByType = new HashMap<>();
            put(usersByTypeStore, usersByType);
        } else {
            usersByType = (HashMap<UserType, List<User>>) get(usersByTypeStore).orElseThrow(IOException::new);
        }
    }

    @Override
    public boolean contains(User user) {
        return users.containsKey(user.getUserName());
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserName(), user);
        if (!usersByType.containsKey(user.getUserType())) {
            List<User> users = new ArrayList<>();
            users.add(user);
            usersByType.put(user.getUserType(), users);
        } else {
            usersByType.get(user.getUserType()).add(user);
        }
        try {
            put(usersStore, users);
            put(usersByTypeStore, usersByType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

    @Override
    public List<User> getAllUsersOfType(UserType type) {
        return usersByType.get(type);
    }
}
