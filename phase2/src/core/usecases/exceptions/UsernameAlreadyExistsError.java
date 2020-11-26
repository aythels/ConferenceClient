package core.usecases.exceptions;

public class UsernameAlreadyExistsError extends Error {

    public UsernameAlreadyExistsError(String msg) {
        super(msg);
    }

    public UsernameAlreadyExistsError() {
        super();
    }
}
