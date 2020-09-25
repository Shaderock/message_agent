package broker.exceptions;

public class UnsupportableOperationException extends ProtocolSyntaxException{
    public UnsupportableOperationException(String message) {
        super(message);
    }
}
