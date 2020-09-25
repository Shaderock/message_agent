package broker.servers;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

@RequiredArgsConstructor
public class HandshakeServer
        extends Worker {
    private final ArrayList<HandshakeHandler> handshakeHandlers = new ArrayList<>();

    private ServerSocket handshakeServerSocket;
    private final int port;
    private boolean isWorking = true;

    @Override
    public void run() {
        work(port);
    }

    private void work(int port) {
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
