package broker.servers;

import java.io.IOException;
import java.net.ServerSocket;

public class HandshakeServer
        extends Thread
        implements Finishable {
    private boolean isRunning = true;
    private HandshakeHandler handshakeHandler;
    private ServerSocket handshakeServerSocket;

    public void work(int port) {
        this.start();
        try {
            handshakeServerSocket = new ServerSocket(port);
            while (isRunning) {
                handshakeHandler = new HandshakeHandler(handshakeServerSocket.accept(), port);
                handshakeHandler.start();
            }
        }
        catch (IOException e) {
            System.out.println("Server socket on port " + port + " has been closed");
        }
    }

    public void finish() {
        isRunning = false;
        if (handshakeHandler != null) {
            handshakeHandler.finish();
        }
        try {
            handshakeServerSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.interrupt();
        System.out.println("HandshakeServer finished its work");
    }
}
