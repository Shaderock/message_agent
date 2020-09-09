package broker.servers;

import java.io.IOException;
import java.net.ServerSocket;

public class HandshakeServer extends Thread {
    private boolean isRunning = true;

    public void work(int port) {
        this.start();
        try {
            ServerSocket handshakeServerSocket = new ServerSocket(port);
            while (isRunning) {
                new HandshakeHandler(handshakeServerSocket.accept()).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        isRunning = false;
        System.out.println("Handshake server finished its work");
    }
}
