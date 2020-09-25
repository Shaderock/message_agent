package broker.communication;

import broker.models.Module;
import broker.models.payload.Payload;
import broker.models.protocols.CommunicationMessageDTO;
import broker.models.protocols.Operation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageGenerator {
    public void sendMessage(Operation operation, Payload payload, Module module) {
        try {
            String responseAsStr = generateMessage(operation, payload);

            module.getOut().print(responseAsStr);
            module.getOut().flush();

            if (module.isConnected()) {
                if (operation != Operation.KEEP_ALIVE) {
                    System.out.println("SENT to id=" + module.getId() + ", message: " + responseAsStr);
                }
            } else {
                if (operation != Operation.KEEP_ALIVE) {
                    System.out.println("SENT, message: " + responseAsStr);
                }
            }
        }
        catch (JsonProcessingException e) {
            System.out.println("Could not serialize payload");
        }
    }

    public String generateMessage(Operation operation, Payload payload) throws JsonProcessingException {
        CommunicationMessageDTO communicationMessageDTO = new CommunicationMessageDTO();
        communicationMessageDTO.setOperation(operation);

        ObjectMapper objectMapper = new ObjectMapper();

        String payloadAsJson = objectMapper.writeValueAsString(payload);
        CommunicationMessageDTO response = new CommunicationMessageDTO();
        response.setOperation(operation);
        response.setPayload(payloadAsJson);
        return objectMapper.writeValueAsString(response);
    }
}
