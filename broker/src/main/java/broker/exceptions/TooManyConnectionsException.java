package broker.exceptions;

public class TooManyConnectionsException extends Exception{
    public TooManyConnectionsException(String message) {
        super(message);
    }
}
