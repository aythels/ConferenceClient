package core.usecases.ports;

import core.entities.User;

public interface IUserRepository {

    boolean contains(User user);

    void addUser(User user);

    User getUser(String username);
}
