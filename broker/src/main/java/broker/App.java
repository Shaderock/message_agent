package broker;

import broker.grpc.services.BrokerService;
import broker.servers.UDPCommunicationHandler;
import broker.utils.TerminalHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.net.SocketAddress;

public class App {
    public static void main(String[] args) {
        Context context = Context.getInstance();

        Server server = ServerBuilder.forPort(context.GRPS_SERVER_PORT)
                .addService(new BrokerService()).build();
        int size = server.getListenSockets().size();
        SocketAddress socketAddress = server.getListenSockets().get(size);
        context.setServer(server);

        try {// todo check for async
            server.start();
        }
        catch (IOException e) {
            System.out.println("Server finished its work");
        }

        TerminalHandler terminalHandler = new TerminalHandler();
        terminalHandler.start();

        UDPCommunicationHandler udpCommunicationHandler = new UDPCommunicationHandler();
        context.getWorkers().add(udpCommunicationHandler);
        udpCommunicationHandler.start();
    }
}
