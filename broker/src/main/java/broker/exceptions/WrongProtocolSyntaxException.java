package broker.exceptions;

public class WrongProtocolSyntaxException extends Exception{
    public WrongProtocolSyntaxException(String message) {
        super(message);
    }
}
