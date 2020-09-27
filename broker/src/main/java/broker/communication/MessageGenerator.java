package broker.communication;

import broker.models.protocols.CommunicationMessageDTO;
import broker.models.protocols.Operation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessageGenerator {

    public String generateMessage(Operation operation) throws JsonProcessingException {
        CommunicationMessageDTO communicationMessageDTO = new CommunicationMessageDTO();
        communicationMessageDTO.setOperation(operation);

        ObjectMapper objectMapper = new ObjectMapper();
        CommunicationMessageDTO response = new CommunicationMessageDTO();
        response.setOperation(operation);
        return objectMapper.writeValueAsString(response);
    }

    public void sendUDPMessage(Operation operation, InetAddress address, int port) throws IOException {
        try {
            byte[] buf;
            DatagramSocket socket = new DatagramSocket();
            String message = generateMessage(operation);
            buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);

            System.out.println("SENT, message: " + message);
        }
        catch (JsonProcessingException e) {
            System.out.println("Could not serialize payload");
        }
    }
}
