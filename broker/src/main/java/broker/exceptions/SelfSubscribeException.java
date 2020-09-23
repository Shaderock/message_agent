package broker.exceptions;

public class SelfSubscribeException extends Exception {
    public SelfSubscribeException(String message) {
        super(message);
    }
}
