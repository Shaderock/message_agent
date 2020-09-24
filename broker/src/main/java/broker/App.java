package broker;

import broker.servers.HandshakeServer;
import broker.utils.BroadcastHandler;
import broker.utils.ConnectionKeeper;
import broker.utils.TerminalHandler;

public class App {
    public static void main(String[] args) {
        HandshakeServer handshakeServer = new HandshakeServer();
        Context context = Context.getInstance();
        ConnectionKeeper connectionKeeper = new ConnectionKeeper();
        connectionKeeper.start();

        context.getWorkers().add(handshakeServer);
        context.getWorkers().add(connectionKeeper);

        TerminalHandler terminalHandler = new TerminalHandler();
        terminalHandler.start();

        BroadcastHandler broadcastHandler = new BroadcastHandler();
        broadcastHandler.start();

        handshakeServer.work(context.HANDSHAKE_PORT);
    }
}
