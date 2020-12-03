package gateways;

import core.entities.User;
import core.entities.UserType;
import core.usecases.ports.IUserRepository;
import gateways.stores.UserStore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository implements IUserRepository {

    private final File file;
    private final UserStore store;

    public UserRepository(String path) throws IOException {
        super(path);
        file = new File(path, "users.ser");
        if (file.createNewFile()) {
            store = new UserStore();
            put(file, store);
        } else {
            store = (UserStore) get(file);
        }
    }

    @Override
    public boolean contains(User user) {
        return store.getUsersByUsername().containsKey(user.getUserName());
    }

    @Override
    public void addUser(User user) {
        store.getUsersByUsername().put(user.getUserName(), user);
        if (!store.getUsersByType().containsKey(user.getUserType())) {
            List<User> users = new ArrayList<>();
            users.add(user);
            store.getUsersByType().put(user.getUserType(), users);
        } else {
            store.getUsersByType().get(user.getUserType()).add(user);
        }
        put(file, store);
    }

    @Override
    public User getUser(String username) {
        return store.getUsersByUsername().get(username);
    }

    @Override
    public List<User> getAllUsersOfType(UserType type) {
        if (store.getUsersByType().containsKey(type)) {
            return store.getUsersByType().get(type);
        } else {
            return new ArrayList<>();
        }
    }
}
