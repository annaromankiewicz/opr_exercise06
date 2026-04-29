package exceptions;

public class NullValueException extends ValueException {

    public NullValueException() {
        super("Parameter is null");
    }

    public NullValueException(String message) {
        super(message);
    }
}