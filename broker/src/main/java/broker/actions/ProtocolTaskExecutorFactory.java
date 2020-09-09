package broker.actions;

import broker.exceptions.WrongProtocolSyntaxException;
import broker.models.payload.NamePayload;
import broker.models.payload.Payload;
import broker.models.protocols.CommunicationMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.naming.OperationNotSupportedException;

public class ProtocolTaskExecutorFactory {
    public ProtocolTaskExecutor createProtocolTaskExecutor(String request)
            throws WrongProtocolSyntaxException, OperationNotSupportedException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            CommunicationMessageDTO communicationMessageDTO =
                    objectMapper.readValue(request, CommunicationMessageDTO.class);

            if (communicationMessageDTO.getOperation() == null) {
                throw new WrongProtocolSyntaxException("Operation not present");
            }

            if (communicationMessageDTO.getPayload() == null) {
                throw new WrongProtocolSyntaxException("Payload not present");
            }

            Payload payload;
            switch (communicationMessageDTO.getOperation()) {
                case HANDSHAKE:
                    payload = objectMapper.readValue(communicationMessageDTO.getPayload(), NamePayload.class);
                    return new HandshakeExecutor((NamePayload) payload);
                case GET_MODULES:
                    return new ModuleListExecutor(null);
                case CLOSE:
                    return new CloseConnectionExecutor(null);
                default:
                    throw new OperationNotSupportedException("Unknown operation type");
            }
        }
        catch (JsonProcessingException e) {
            System.out.println("Could not deserialize JSON");
            throw new WrongProtocolSyntaxException("Could not deserialize JSON");
        }
    }
}
