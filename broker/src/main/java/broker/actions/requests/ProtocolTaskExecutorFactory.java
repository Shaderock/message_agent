package broker.actions.requests;

import broker.exceptions.OperationNotPresentException;
import broker.exceptions.UnsupportableOperationException;
import broker.exceptions.WrongPayloadSchemeException;
import broker.models.payload.DirectMessageRequestPayload;
import broker.models.payload.Payload;
import broker.models.payload.TypePayload;
import broker.models.protocols.CommunicationMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.naming.OperationNotSupportedException;

public class ProtocolTaskExecutorFactory {
    public ProtocolTaskExecutor createProtocolTaskExecutor(String request)
            throws WrongPayloadSchemeException, OperationNotPresentException, UnsupportableOperationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            CommunicationMessageDTO communicationMessageDTO =
                    objectMapper.readValue(request, CommunicationMessageDTO.class);

            if (communicationMessageDTO.getOperation() == null) {
                throw new OperationNotPresentException("Operation not present", null);
            }

            Payload payload;

            // check non-payload operations first

            switch (communicationMessageDTO.getOperation()) {
                case GET_MODULES:
                    return new ModuleListExecutor(null);
                case CLOSE:
                    return new CloseConnectionExecutor(null);
            }

            // then check payload-require operations

            if (communicationMessageDTO.getPayload() == null) {
                throw new WrongPayloadSchemeException("Required payload not present",
                        communicationMessageDTO.getOperation());
            }

            switch (communicationMessageDTO.getOperation()) {
                case HANDSHAKE:
                    payload = objectMapper.readValue(communicationMessageDTO.getPayload(), TypePayload.class);
                    return new HandshakeExecutor((TypePayload) payload);
                case DIRECT_MESSAGE:
                    payload = objectMapper.readValue(communicationMessageDTO.getPayload(),
                            DirectMessageRequestPayload.class);
                    return new DirectMessageExecutor((DirectMessageRequestPayload) payload);
                default:
                    throw new UnsupportableOperationException("Unknown operation type");
            }
        }
        catch (JsonProcessingException e) {
            System.out.println("Could not deserialize JSON");
            throw new WrongPayloadSchemeException("Could not deserialize JSON");
        }
    }
}
