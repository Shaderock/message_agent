package broker.servers;

import broker.Context;

import java.io.IOException;
import java.net.ServerSocket;

public class HandshakeServer extends Thread {
    private boolean isRunning = true;
    Context context = Context.getInstance();

    public void work() {
        this.start();
        try {
            ServerSocket handshakeServerSocket = new ServerSocket(context.HANDSHAKE_PORT);
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
