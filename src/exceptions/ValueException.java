package exceptions;

public class ValueException extends Exception{
    private int whichValue;

    public ValueException() {
        super();
        whichValue = -1;
    }

    public ValueException(int status) {
        super();
        this.whichValue = status;
    }

    public ValueException(String msg) {
        super(msg);
    }

    public int getWhichValue() {
        return whichValue;
    }

}
