package broker;

import broker.servers.HandshakeServer;
import broker.servers.UDPCommunicationHandler;
import broker.utils.ConnectionKeeper;
import broker.utils.TerminalHandler;

public class App {
    public static void main(String[] args) {
        Context context = Context.getInstance();

        HandshakeServer handshakeServer = new HandshakeServer(context.TCP_HANDSHAKE_PORT);
        context.getWorkers().add(handshakeServer);
        handshakeServer.start();

        ConnectionKeeper connectionKeeper = new ConnectionKeeper();
        context.getWorkers().add(connectionKeeper);
        connectionKeeper.start();

        TerminalHandler terminalHandler = new TerminalHandler();
        terminalHandler.start();

        UDPCommunicationHandler udpCommunicationHandler = new UDPCommunicationHandler();
        context.getWorkers().add(udpCommunicationHandler);
        udpCommunicationHandler.start();
    }
}
