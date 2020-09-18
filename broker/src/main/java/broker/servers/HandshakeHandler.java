package broker.servers;

import broker.actions.HandshakeExecutor;
import broker.actions.ProtocolTaskExecutorFactory;
import broker.exceptions.WrongProtocolSyntaxException;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.protocols.Operation;
import broker.utils.ResponseGenerator;

import javax.naming.OperationNotSupportedException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HandshakeHandler extends Thread {
    private final Socket moduleSocket;
    private final ResponseGenerator responseGenerator = new ResponseGenerator();
    private final int connectedToPort;

    public HandshakeHandler(Socket moduleSocket, int connectedToPort) {
        this.moduleSocket = moduleSocket;
        this.connectedToPort = connectedToPort;
    }

    @Override
    public void run() {
        try {
            handshake(moduleSocket);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handshake(Socket acceptedSocket) throws IOException {
        PrintWriter out = new PrintWriter(acceptedSocket.getOutputStream(), true);
        DataInputStream in = new DataInputStream(acceptedSocket.getInputStream());

        while (in.available() == 0) {
        }

        byte[] requestAsBytes = new byte[in.available()];
        int i = 0;
        while (in.available() != 0) {
            requestAsBytes[i++] = in.readByte();
        }

        String request = new String(requestAsBytes, StandardCharsets.UTF_8);
        System.out.print("Message Received: " + request);

        ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
        HandshakeExecutor handshakeExecutor;
        try {
            handshakeExecutor = (HandshakeExecutor) protocolTaskExecutorFactory
                    .createProtocolTaskExecutor(request);
            handshakeExecutor.setConnectedPort(connectedToPort);
            handshakeExecutor.execute(moduleSocket, out);
        }
        catch (WrongProtocolSyntaxException e) {
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME), out);
        }
        catch (OperationNotSupportedException e) {
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new CodePayload(Code.UNSUPPORTABLE_OPERATION), out);
        }

    }
}
