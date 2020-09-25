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
        implements OnConnectionEstablishedListener {
    private final Socket moduleSocket;
    private final MessageGenerator messageGenerator = new MessageGenerator();
    private final int connectedToPort;
    private CommunicationHandler communicationHandler;
    private PrintWriter out;

    public HandshakeHandler(Socket moduleSocket, int connectedToPort) {
        this.moduleSocket = moduleSocket;
        this.connectedToPort = connectedToPort;
    }

    @Override
    public void run() {
        handshake(moduleSocket);
    }

    private void handshake(Socket acceptedSocket) {
        try {
            out = new PrintWriter(acceptedSocket.getOutputStream(), true);
            DataInputStream in = new DataInputStream(acceptedSocket.getInputStream());

            MessageListener messageListener = new MessageListener(Module.builder()
                    .connected(false)
                    .in(in)
                    .build());
            String message = messageListener.listen();
            System.out.print("RECEIVED, message: " + message);

            ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
            HandshakeExecutor handshakeExecutor;
            handshakeExecutor = (HandshakeExecutor) protocolTaskExecutorFactory
                    .createProtocolTaskExecutor(message);
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
                    new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME),
                    Module.builder()
                            .out(out)
                            .connected(false)
                            .build());
        }
        catch (UnsupportableOperationException | OperationNotPresentException e) {
            messageGenerator.sendMessage(Operation.HANDSHAKE,
                    new CodePayload(Code.UNSUPPORTABLE_OPERATION),
                    Module.builder()
                            .out(out)
                            .connected(false)
                            .build());
        }
        catch (IOException e) {
            System.out.println("HandshakeHandler does not serve an unconnected module anymore");
        }
        catch (InterruptedException e) {
            System.out.println("MessageListener is not listening for a socket anymore");
        }
    }

    @Override
    public void onConnectionEstablished(Module module) {
        communicationHandler = new CommunicationHandler(module);
        communicationHandler.start();
    }

    @Override
    public void interrupt() {
        super.interrupt();
        if (communicationHandler != null) {
            communicationHandler.interrupt();
        }
        System.out.println("HandshakeHandler finished its work");
    }
}
