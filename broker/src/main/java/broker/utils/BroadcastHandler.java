package broker.utils;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.models.protocols.Operation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastHandler extends Thread {
    private static DatagramSocket socket = null;
    private final MessageGenerator messageGenerator = new MessageGenerator();

    @Override
    public void run() {
        sendAliveBroadcast();
    }

    public void sendAliveBroadcast() {
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
            String broadcastMessage = messageGenerator
                    .generateMessage(Operation.BROKER_IS_ALIVE, null);

            byte[] buffer = broadcastMessage.getBytes();

            DatagramPacket packet
                    = new DatagramPacket(buffer, buffer.length,
                    InetAddress.getByName("192.168.0.255"), Context.getInstance().UDP_MODULE_LISTENED_PORT);
            socket.send(packet);
            socket.close();
        }
        catch (IOException e) {
            System.out.println("Could not send alive broadcast");
        }
    }
}
