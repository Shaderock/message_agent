package broker.actions;

import broker.Context;
import broker.exceptions.TooManyConnectionsException;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.*;
import broker.models.protocols.Operation;
import broker.servers.HandshakeServer;
import broker.utils.ResponseGenerator;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HandshakeExecutor extends ProtocolTaskExecutor {
    private final ResponseGenerator responseGenerator;

    @Setter
    private int connectedPort;

    public HandshakeExecutor(TypePayload typePayload) {
        super(typePayload);
        responseGenerator = new ResponseGenerator();
    }

    public void execute(Socket moduleSocket, PrintWriter moduleOutput) {
        Type moduleType = ((TypePayload) getPayload()).getType();

        try {
            if (connectModuleToTheSystem(moduleSocket, moduleType,
                    moduleOutput, responseGenerator, connectedPort)) {
                responseGenerator.sendResponse(Operation.HANDSHAKE, new CodePayload(Code.OK), moduleOutput);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized boolean connectModuleToTheSystem(Socket moduleSocket,
                                                                 Type moduleType,
                                                                 PrintWriter moduleOutput,
                                                                 ResponseGenerator responseGenerator,
                                                                 int connectedPort)
            throws IOException {

        Context context = Context.getInstance();

        int amountModulesConnected = 0;
        for (PortData portsDatum : context.getPortsData()) {
            for (Module module : portsDatum.getModules()) {
                if (module.getModuleType() == moduleType) {
                    amountModulesConnected++;
                }
            }
        }

        if (amountModulesConnected >= moduleType.getMaxConnections()) {
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new CodePayload(Code.NOT_ENOUGH_PLACE_FOR_NEW_CONNECTION), moduleOutput);
            moduleSocket.close();
            return false;
        }

        for (PortData portsDatum : context.getPortsData()) {
            if (connectedPort != context.HANDSHAKE_PORT &&
                    portsDatum.getPort() == connectedPort &&
                    portsDatum.getModules().size() < context.MAX_SOCKETS_PER_PORT) {
                portsDatum.getModules().add(new Module(moduleSocket,
                        moduleType, context.getNextModuleId()));
                context.setNextModuleId(context.getNextModuleId());
                System.out.println("connected to port " + connectedPort);
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
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new RedirectPayload(Code.REDIRECT, portWithFreeSlot), moduleOutput);
            return false;
        } else {
            try {
                int port = startHandshakeServer();
                responseGenerator.sendResponse(Operation.HANDSHAKE,
                        new RedirectPayload(Code.REDIRECT, port), moduleOutput);
                moduleSocket.close();
                return false;
            }
            catch (TooManyConnectionsException e) {
                responseGenerator.sendResponse(Operation.HANDSHAKE,
                        new CodePayload(Code.NOT_ENOUGH_PLACE_FOR_NEW_CONNECTION), moduleOutput);
                moduleSocket.close();
                return false;
            }
        }
    }

    private static synchronized int startHandshakeServer() throws TooManyConnectionsException {
        Context context = Context.getInstance();
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
