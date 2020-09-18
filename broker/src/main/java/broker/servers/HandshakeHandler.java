package broker.servers;

import broker.actions.HandshakeExecutor;
import broker.actions.ProtocolTaskExecutorFactory;
import broker.exceptions.WrongProtocolSyntaxException;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.protocols.Operation;
import broker.utils.ResponseGenerator;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandshakeHandler extends Thread {
    private final Socket moduleSocket;
    private final ResponseGenerator responseGenerator = new ResponseGenerator();

    public HandshakeHandler(Socket moduleSocket) {
        this.moduleSocket = moduleSocket;
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
        BufferedReader in = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));

//        ObjectInputStream ois = new ObjectInputStream(moduleSocket.getInputStream());
        //convert ObjectInputStream object to String
//        String request = (String) ois.readObject();
//        System.out.println("Message Received: " + request);

        String request = in.readLine();
        System.out.println("Message Received: " + request);

        ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
        HandshakeExecutor handshakeExecutor;
        try {
            handshakeExecutor = (HandshakeExecutor) protocolTaskExecutorFactory
                    .createProtocolTaskExecutor(request);
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
