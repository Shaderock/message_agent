package broker.servers;

import broker.actions.requests.HandshakeExecutor;
import broker.actions.not_requests.OnConnectionEstablishedListener;
import broker.actions.requests.ProtocolTaskExecutorFactory;
import broker.communication.MessageListener;
import broker.exceptions.OperationNotPresentException;
import broker.exceptions.UnsupportableOperationException;
import broker.exceptions.WrongPayloadSchemeException;
import broker.models.Module;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.protocols.Operation;
import broker.communication.ResponseGenerator;
import lombok.Setter;

import javax.naming.OperationNotSupportedException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HandshakeHandler
        extends Thread
        implements OnConnectionEstablishedListener {
    private final Socket moduleSocket;
    private final ResponseGenerator responseGenerator = new ResponseGenerator();
    private final int connectedToPort;

    @Setter
    private OnConnectionEstablishedListener onConnectionEstablishedListener;

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

        MessageListener messageListener = new MessageListener();
        String request = messageListener.listen(in);

        System.out.print("Message Received: " + request);

        ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
        HandshakeExecutor handshakeExecutor;
        try {
            handshakeExecutor = (HandshakeExecutor) protocolTaskExecutorFactory
                    .createProtocolTaskExecutor(request);
            handshakeExecutor.setConnectedPort(connectedToPort);
            handshakeExecutor.setOnConnectionEstablishedListener(this);
            handshakeExecutor.execute(new Module(acceptedSocket, in, out, null, 0));
        }
        catch (WrongPayloadSchemeException e) {
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME), out);
        }
        catch (UnsupportableOperationException | OperationNotPresentException e) {
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new CodePayload(Code.UNSUPPORTABLE_OPERATION), out);
        }

    }

    @Override
    public void onConnectionEstablished(Module module) {
        CommunicationHandler communicationHandler = new CommunicationHandler(module);
        communicationHandler.start();
    }
}
