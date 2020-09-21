package broker.servers;

import java.io.IOException;
import java.net.ServerSocket;

public class HandshakeServer
        extends Thread
        implements Finishable {
    private boolean isRunning = true;
    private HandshakeHandler handshakeHandler;

    public void work(int port) {
        this.start();
        try {
            ServerSocket handshakeServerSocket = new ServerSocket(port);
            while (isRunning) {
                handshakeHandler = new HandshakeHandler(handshakeServerSocket.accept(), port);
                handshakeHandler.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        isRunning = false;
        if (handshakeHandler != null) {
            handshakeHandler.finish();
        }
        System.out.println("Handshake server finished its work");
    }
}
