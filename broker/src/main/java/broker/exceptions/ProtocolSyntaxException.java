package broker.exceptions;

import broker.models.protocols.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProtocolSyntaxException extends Exception {
    private Operation operation;

    public ProtocolSyntaxException(String message) {
        super(message);
    }

    public ProtocolSyntaxException(String message, Operation operation) {
        super(message);
        this.operation = operation;
    }
}
