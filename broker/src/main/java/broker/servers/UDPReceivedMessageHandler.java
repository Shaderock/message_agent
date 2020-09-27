package broker.servers;

import broker.communication.MessageGenerator;
import broker.models.CommunicationMessageDTO;
import broker.models.Operation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

@RequiredArgsConstructor
public class UDPReceivedMessageHandler extends Thread {
    private final DatagramPacket packetWithMessage;
    private final byte[] buf;

    @Override
    public void run() {
        handleMessage();
    }

    private void handleMessage() {
        InetAddress address = packetWithMessage.getAddress();
        int port = packetWithMessage.getPort();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        String received = new String(packet.getData(), 0, packet.getLength());
        MessageGenerator messageGenerator = new MessageGenerator();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CommunicationMessageDTO communicationMessageDTO =
                    objectMapper.readValue(received, CommunicationMessageDTO.class);
            if (communicationMessageDTO.getOperation() == Operation.MODULE_IS_ALIVE) {
                messageGenerator.sendUDPMessage(Operation.BROKER_IS_ALIVE, address, port);
            }
        }
        catch (JsonProcessingException e) {
            System.out.println("UDPReceivedMessageHandler could not deserialize JSON");
        }
        catch (IOException e) {
            System.out.println("UDPReceivedMessageHandler is not listening for UDP messages anymore");
        }
    }

    @Override
    public void interrupt() {
        System.out.println("UPDPReceivedMessageHandler finished its work");
    }
}
