package broker.actions.requests;

import broker.Context;
import broker.actions.not_requests.OnConnectionEstablishedListener;
import broker.communication.MessageGenerator;
import broker.exceptions.TooManyConnectionsException;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.*;
import broker.models.protocols.Operation;
import broker.servers.HandshakeServer;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HandshakeExecutor extends ProtocolTaskExecutor {
    private final MessageGenerator messageGenerator;

    @Setter
    private OnConnectionEstablishedListener onConnectionEstablishedListener;

    @Setter
    private int connectedPort;

    public HandshakeExecutor(TypePayload typePayload) {
        super(typePayload);
        messageGenerator = new MessageGenerator();
    }

    public void execute(Module module) {
        Type moduleType = ((TypePayload) getPayload()).getType();
        Socket moduleSocket = module.getSocket();
        DataInputStream moduleInput = module.getIn();
        PrintWriter moduleOutput = module.getOut();

        try {
            if (connectModuleToTheSystem(moduleSocket, moduleInput, moduleOutput,
                    moduleType, messageGenerator, connectedPort)) {
                messageGenerator.sendMessage(Operation.HANDSHAKE, new CodePayload(Code.OK),
                        Module.builder()
                                .out(moduleOutput)
                                .id(-1)
                                .build());

                for (PortData portsDatum : Context.getInstance().getPortsData()) {
                    for (Module connectedModule : portsDatum.getModules()) {
                        if (connectedModule.getSocket() == moduleSocket) {
                            if (onConnectionEstablishedListener != null) {
                                onConnectionEstablishedListener.onConnectionEstablished(connectedModule);
                            } else {
                                System.out.println("Should set OnConnectionEstablishedListener before calling");
                            }
                            break;
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized boolean connectModuleToTheSystem(Socket moduleSocket,
                                                                 DataInputStream moduleInput,
                                                                 PrintWriter moduleOutput,
                                                                 Type moduleType,
                                                                 MessageGenerator messageGenerator,
                                                                 int connectedPort)
            throws IOException {

        Context context = Context.getInstance();

        int amountModulesConnected = 0;
        for (PortData portsDatum : context.getPortsData()) {
            for (Module module : portsDatum.getModules()) {
                if (module.getType() == moduleType) {
                    amountModulesConnected++;
                }
            }
        }

        if (amountModulesConnected >= moduleType.getMaxConnections()) {
            messageGenerator.sendMessage(Operation.HANDSHAKE,
                    new CodePayload(Code.NOT_ENOUGH_PLACE_FOR_NEW_CONNECTION),
                    Module.builder()
                            .out(moduleOutput)
                            .id(-1)
                            .build());
            moduleSocket.close();
            return false;
        }

        for (PortData portsDatum : context.getPortsData()) {
            if (connectedPort != context.HANDSHAKE_PORT &&
                    portsDatum.getPort() == connectedPort &&
                    portsDatum.getModules().size() < context.MAX_SOCKETS_PER_PORT) {
                Module moduleToConnect = Module
                        .builder()
                        .socket(moduleSocket)
                        .in(moduleInput)
                        .out(moduleOutput)
                        .type(moduleType)
                        .id(context.getNextModuleId())
                        .notifiersIds(new ArrayList<Integer>())
                        .build();

                moduleToConnect.getSocket().setSoTimeout(1000);

                portsDatum.getModules().add(moduleToConnect);
                context.setNextModuleId(context.getNextModuleId() + 1);
                System.out.println("Connected to port " + connectedPort);

                return true;
            }
        }

        boolean isFoundFreeSlot = false;
        int portWithFreeSlot = 0;
        for (PortData portsDatum : context.getPortsData()) {
            if (portsDatum.getModules().size() < context.MAX_SOCKETS_PER_PORT) {
                isFoundFreeSlot = true;
                portWithFreeSlot = portsDatum.getPort();
                break;
            }
        }

        if (isFoundFreeSlot) {
            messageGenerator.sendMessage(Operation.HANDSHAKE,
                    new RedirectPayload(Code.REDIRECT, portWithFreeSlot),
                    Module.builder()
                            .out(moduleOutput)
                            .id(-1)
                            .build());
            return false;
        } else {
            try {
                int port = startHandshakeServer();
                messageGenerator.sendMessage(Operation.HANDSHAKE,
                        new RedirectPayload(Code.REDIRECT, port),
                        Module.builder()
                                .out(moduleOutput)
                                .id(-1)
                                .build());
                moduleSocket.close();
                return false;
            }
            catch (TooManyConnectionsException e) {
                messageGenerator.sendMessage(Operation.HANDSHAKE,
                        new CodePayload(Code.NOT_ENOUGH_PLACE_FOR_NEW_CONNECTION),
                        Module.builder()
                                .out(moduleOutput)
                                .id(-1)
                                .build());
                moduleSocket.close();
                return false;
            }
        }
    }

    private static synchronized int startHandshakeServer() throws TooManyConnectionsException {
        final Context context = Context.getInstance();
        for (int freePort = context.COMMUNICATION_PORT;
             freePort < context.COMMUNICATION_PORT + context.MAX_COMMUNICATION_PORTS; freePort++) {

            boolean isFoundFreePort = true;
            for (PortData portsDatum : context.getPortsData()) {
                if (portsDatum.getPort() == freePort) {
                    isFoundFreePort = false;
                    break;
                }
            }

            if (isFoundFreePort) {
                final HandshakeServer handshakeServer = new HandshakeServer();

                final int finalFreePort = freePort;
                context.getPortsData().add(new PortData(freePort));
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        context.getWorkers().add(handshakeServer);
                        handshakeServer.work(finalFreePort);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();

                return freePort;
            }
        }

        throw new TooManyConnectionsException("Can not accept any other connection");
    }
}
