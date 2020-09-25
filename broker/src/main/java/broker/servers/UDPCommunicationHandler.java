package broker.servers;

import broker.Context;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPCommunicationHandler extends Worker {
    private UDPReceivedMessageHandler udpReceivedMessageHandler;
    private boolean isWorking = true;
    private DatagramSocket socketToReceiveMessage;

    @Override
    public void run() {
        acceptMessage();
    }

    public void acceptMessage() {
        Context context = Context.getInstance();
        while (isWorking) {
            try {
                socketToReceiveMessage = new DatagramSocket(context.UDP_BROKER_LISTENED_PORT);
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socketToReceiveMessage.receive(packet);

                udpReceivedMessageHandler = new UDPReceivedMessageHandler(packet, buf);
                udpReceivedMessageHandler.start();
            }
            catch (IOException e) {
                System.out.println("UDPCommunicationHandler is not waiting for UDP messages anymore");
            }
        }
    }

    @Override
    public void interrupt() {
        isWorking = false;
        if (udpReceivedMessageHandler != null) {
            udpReceivedMessageHandler.interrupt();
        }

        if (socketToReceiveMessage != null){
            socketToReceiveMessage.close();
        }
        System.out.println("UDPCommunicationHandler finished its work");
    }
}
