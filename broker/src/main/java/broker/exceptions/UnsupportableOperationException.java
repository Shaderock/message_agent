package broker.exceptions;

import broker.models.protocols.Operation;

public class UnsupportableOperationException extends ProtocolSyntaxException{

    public UnsupportableOperationException(String message) {
        super(message);
    }

    public UnsupportableOperationException(String message, Operation operation) {
        super(message, operation);
    }
}
