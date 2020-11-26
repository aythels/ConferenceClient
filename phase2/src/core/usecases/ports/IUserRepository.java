package core.usecases.ports;

import core.entities.User;
import core.entities.UserType;

import java.util.List;

public interface IUserRepository {

    boolean contains(User user);

    void addUser(User user);

    User getUser(String username);

    List<User> getAllUsersOfType(UserType type);
}
