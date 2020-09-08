package broker.actions;

import broker.exceptions.WrongProtocolSyntaxException;
import broker.models.payload.NamePayload;
import broker.models.protocols.Operation;
import broker.models.protocols.PayloadProtocol;
import broker.models.protocols.Protocol;

public class ProtocolTaskExecutorFactory {
    public ProtocolTaskExecutor createProtocolTaskExecutor(Protocol protocol) throws WrongProtocolSyntaxException {
        if (protocol instanceof PayloadProtocol) {
            if (protocol.getOperation() == Operation.HANDSHAKE) {
                if (((PayloadProtocol) protocol).getPayload() instanceof NamePayload) {
                    return new HandshakeExecutor();
                }
            }
        } else if (protocol.getOperation() == Operation.GET_MODULES) {
            return new ModuleListExecutor();
        } else if (protocol.getOperation() == Operation.CLOSE) {
            return new CloseConnectionExecutor();
        }

        throw new WrongProtocolSyntaxException("Protocol with operation " +
                protocol.getOperation().name() + " has wrong syntax");
    }
}
