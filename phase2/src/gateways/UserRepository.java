package gateways;

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

    @SuppressWarnings("unchecked")
    public UserRepository(String path) throws IOException {
        super(path);
        usersStore = new File(path, "/users.ser");
        usersByTypeStore = new File(path, "/usersByType.ser");
        if (usersStore.createNewFile()) {
            users = new HashMap<>();
            put(usersStore, users);
        } else {
            users = (HashMap<String, User>) get(usersStore);
        }
        if (usersByTypeStore.createNewFile()) {
            usersByType = new HashMap<>();
            put(usersByTypeStore, usersByType);
        } else {
            usersByType = (HashMap<UserType, List<User>>) get(usersByTypeStore);
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
        put(usersStore, users);
        put(usersByTypeStore, usersByType);
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
        if (usersByType.containsKey(type)) {
            return usersByType.get(type);
        } else {
            return new ArrayList<>();
        }
    }
}
