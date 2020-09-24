package broker.communication;

import broker.models.payload.Payload;
import broker.models.protocols.CommunicationMessageDTO;
import broker.models.protocols.Operation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;

public class MessageGenerator {
    public void sendMessage(Operation operation, Payload payload, PrintWriter moduleSocketOutput) {
        CommunicationMessageDTO communicationMessageDTO = new CommunicationMessageDTO();
        communicationMessageDTO.setOperation(operation);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String payloadAsJson = objectMapper.writeValueAsString(payload);
            CommunicationMessageDTO response = new CommunicationMessageDTO();
            response.setOperation(operation);
            response.setPayload(payloadAsJson);
            String responseAsStr = objectMapper.writeValueAsString(response);
            moduleSocketOutput.print(responseAsStr);
            moduleSocketOutput.flush();

            System.out.println("message SENT, message: " + responseAsStr);
        }
        catch (JsonProcessingException e) {
            System.out.println("Could not serialize payload");
        }
    }
}
