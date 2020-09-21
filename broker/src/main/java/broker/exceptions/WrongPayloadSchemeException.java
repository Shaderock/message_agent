package broker.exceptions;

import broker.models.protocols.Operation;

public class WrongPayloadSchemeException extends ProtocolSyntaxException {
    public WrongPayloadSchemeException(String message) {
        super(message);
    }

    public WrongPayloadSchemeException(String message, Operation operation) {
        super(message, operation);
    }
}
