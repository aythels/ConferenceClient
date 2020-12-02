package core.usecases;

import core.entities.User;
import core.usecases.exceptions.IncorrectPasswordError;
import core.usecases.exceptions.LoginNotFoundError;
import core.usecases.exceptions.UserAlreadyExistsError;
import core.usecases.ports.ILoginRepository;
import core.usecases.ports.IUserRepository;

public class LoginInteractor {

    private final ILoginRepository logins;
    private final IUserRepository users;

    public LoginInteractor(ILoginRepository logins, IUserRepository users) {
        this.logins = logins;
        this.users = users;
    }

    public void registerUser(User user, String password) throws UserAlreadyExistsError {
        if (logins.contains(user.getUserName()) || users.contains(user))
            throw new UserAlreadyExistsError("Username already exists in user repository");
        logins.addLogin(user.getUserName(), password);
        users.addUser(user);
    }

    public User login(String username, String password) throws LoginNotFoundError {
        if (!logins.contains(username))
            throw new LoginNotFoundError("Login doesn't exist in login repository");
        if (!logins.getPassword((username)).equals(password))
            throw new IncorrectPasswordError("Incorrect password");
        return users.getUser(username);
    }
}
