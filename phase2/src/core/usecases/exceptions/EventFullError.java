package core.usecases.exceptions;

public class EventFullError extends Error {

    public EventFullError(String msg) {
        super(msg);
    }

    public EventFullError() {
        super();
    }
}
