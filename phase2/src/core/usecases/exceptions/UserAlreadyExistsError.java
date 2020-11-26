package core.usecases.exceptions;

public class UserAlreadyExistsError extends Error {

    public UserAlreadyExistsError(String msg) {
        super(msg);
    }

    public UserAlreadyExistsError() {
        super();
    }
}
