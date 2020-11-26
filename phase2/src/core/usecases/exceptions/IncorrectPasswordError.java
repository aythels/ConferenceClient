package core.usecases.exceptions;

public class IncorrectPasswordError extends Error {

    public IncorrectPasswordError(String msg) {
        super(msg);
    }

    public IncorrectPasswordError() {
        super();
    }
}
