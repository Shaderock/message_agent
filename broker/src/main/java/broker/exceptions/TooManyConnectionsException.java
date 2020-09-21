package broker.exceptions;

public class TooManyConnectionsException extends ProtocolSyntaxException {
    public TooManyConnectionsException(String message) {
        super(message);
    }
}
