package broker.exceptions;

public class AllPortsFullException extends Exception {
    public AllPortsFullException(String message) {
        super(message);
    }
}
