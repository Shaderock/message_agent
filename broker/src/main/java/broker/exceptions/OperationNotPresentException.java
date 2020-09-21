package broker.exceptions;

import broker.models.protocols.Operation;

public class OperationNotPresentException extends ProtocolSyntaxException {
    public OperationNotPresentException(String message) {
        super(message);
    }

    public OperationNotPresentException(String message, Operation operation) {
        super(message, operation);
    }
}
