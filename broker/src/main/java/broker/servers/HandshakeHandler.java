package broker.servers;

import broker.actions.not_requests.OnConnectionEstablishedListener;
import broker.actions.requests.HandshakeExecutor;
import broker.actions.requests.ProtocolTaskExecutorFactory;
import broker.communication.MessageGenerator;
import broker.communication.MessageListener;
import broker.exceptions.OperationNotPresentException;
import broker.exceptions.UnsupportableOperationException;
import broker.exceptions.WrongPayloadSchemeException;
import broker.models.Module;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.protocols.Operation;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HandshakeHandler
        extends Thread
        implements OnConnectionEstablishedListener,
        Finishable {
    private final Socket moduleSocket;
    private final MessageGenerator messageGenerator = new MessageGenerator();
    private final int connectedToPort;
    private CommunicationHandler communicationHandler;

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

        System.out.print("message RECEIVED, message: " + request);

        ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
        HandshakeExecutor handshakeExecutor;
        try {
            handshakeExecutor = (HandshakeExecutor) protocolTaskExecutorFactory
                    .createProtocolTaskExecutor(request);
            handshakeExecutor.setConnectedPort(connectedToPort);
            handshakeExecutor.setOnConnectionEstablishedListener(this);
            handshakeExecutor.execute(Module
                    .builder()
                    .socket(acceptedSocket)
                    .in(in)
                    .out(out)
                    .notifiersIds(new ArrayList<Integer>())
                    .build());
        }
        catch (WrongPayloadSchemeException e) {
            messageGenerator.sendMessage(Operation.HANDSHAKE,
                    new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME), out);
        }
        catch (UnsupportableOperationException | OperationNotPresentException e) {
            messageGenerator.sendMessage(Operation.HANDSHAKE,
                    new CodePayload(Code.UNSUPPORTABLE_OPERATION), out);
        }
    }

    @Override
    public void onConnectionEstablished(Module module) {
        communicationHandler = new CommunicationHandler(module);
        communicationHandler.start();
    }

    @Override
    public void finish() {
        if (communicationHandler != null) {
            communicationHandler.finish();
        }
    }
}
