package broker.actions;

import broker.Context;
import broker.exceptions.AllPortsFullException;
import broker.exceptions.ModuleAlreadyExistsException;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.payload.NamePayload;
import broker.models.payload.RedirectPayload;
import broker.models.protocols.Operation;
import broker.servers.HandshakeServer;
import broker.utils.ResponseGenerator;

import java.io.PrintWriter;
import java.net.Socket;

public class HandshakeExecutor extends ProtocolTaskExecutor {
    public HandshakeExecutor(NamePayload namePayload) {
        super(namePayload);
    }

    public void execute(Socket moduleSocket, PrintWriter moduleOutput) {
        String moduleName = ((NamePayload) getPayload()).getName();
        ResponseGenerator responseGenerator = new ResponseGenerator();
        try {
            connectModuleToTheSystem(moduleSocket, moduleName);
        }
        catch (ModuleAlreadyExistsException e) {
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new CodePayload(Code.NAME_ALREADY_EXISTS), moduleOutput);
        }
        catch (AllPortsFullException e) {
            int port = startHandshakeServer();
            responseGenerator.sendResponse(Operation.HANDSHAKE,
                    new RedirectPayload(Code.REDIRECT, port), moduleOutput);
        }
    }

    private static synchronized void connectModuleToTheSystem(Socket moduleSocket, String moduleName)
            throws ModuleAlreadyExistsException, AllPortsFullException {

        Context context = Context.getInstance();
        for (PortData portsDatum : context.getPortsData()) {
            for (Module module : portsDatum.getModules()) {
                if (module.getModuleName().equals(moduleName)) {
                    throw new ModuleAlreadyExistsException("Module with name " + moduleName + " already exists");
                }
            }
        }

        boolean isFoundFreeSlot = false;
        for (PortData portsDatum : context.getPortsData()) {
            if (portsDatum.getModules().size() < context.MAX_SOCKETS_PER_PORT) {
                isFoundFreeSlot = true;
                portsDatum.getModules().add(new Module(moduleSocket, moduleName));
                break;
            }
        }

        if (!isFoundFreeSlot) {
            throw new AllPortsFullException("Not enough place to accept module");
        }
    }

    private static synchronized int startHandshakeServer() {
        Context context = Context.getInstance();
        // todo добавить обработку перехода за границы ограничений
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

        return 17102;
    }
}
