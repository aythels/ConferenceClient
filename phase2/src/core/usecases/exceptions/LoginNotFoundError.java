package core.usecases.exceptions;

public class LoginNotFoundError extends Error {

    public LoginNotFoundError(String msg) {
        super(msg);
    }

    public LoginNotFoundError() {
        super();
    }
}
