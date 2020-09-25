package broker.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class HandshakeServer
        extends Worker {
    private final ArrayList<HandshakeHandler> handshakeHandlers = new ArrayList<>();

    private ServerSocket handshakeServerSocket;
    private int port;
    private boolean isWorking = true;

    public void work(int port) {
        this.start();
        this.port = port;
        try {
            handshakeServerSocket = new ServerSocket(port);
            while (isWorking) {
                HandshakeHandler handshakeHandler =
                        new HandshakeHandler(handshakeServerSocket.accept(), port);
                handshakeHandler.start();
                handshakeHandlers.add(handshakeHandler);
            }
        }
        catch (IOException e) {
            System.out.println("Server socket on port " + port + " has been closed");
        }
    }

    @Override
    public void interrupt() {
        isWorking = false;
        try {
            handshakeServerSocket.close();
        }
        catch (IOException e) {
            System.out.println("Server socket on port " + port + " has been closed");
        }
        for (HandshakeHandler handshakeHandler : handshakeHandlers) {
            handshakeHandler.interrupt();
        }
        System.out.println("HandshakeServer on port=" + port + " finished its work");
    }
}
