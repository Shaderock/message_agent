package broker;

import broker.servers.HandshakeServer;
import broker.utils.ConnectionKeeper;

public class App {
    public static void main(String[] args) {
        HandshakeServer handshakeServer = new HandshakeServer();
        Context context = Context.getInstance();
        context.getHandshakeServers().add(handshakeServer);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ConnectionKeeper connectionKeeper = new ConnectionKeeper();
                connectionKeeper.keepConnection();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        handshakeServer.work(context.HANDSHAKE_PORT);
//        ConnectionKeeper connectionKeeper = new ConnectionKeeper();
//        connectionKeeper.keepConnection();
    }
}
