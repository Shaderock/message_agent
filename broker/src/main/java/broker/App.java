package broker;

import broker.servers.HandshakeServer;

public class App {
    public static void main(String[] args) {
        HandshakeServer handshakeServer = new HandshakeServer();
        Context context = Context.getInstance();
        context.getHandshakeServers().add(handshakeServer);
        handshakeServer.work(context.HANDSHAKE_PORT);
    }
}
