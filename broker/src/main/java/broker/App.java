package broker;

import broker.services.BrokerService;
import broker.servers.UDPCommunicationHandler;
import broker.utils.TerminalHandler;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Context context = Context.getInstance();

        Server server = ServerBuilder.forPort(context.GRPC_SERVER_PORT)
                .addService(new BrokerService()).build();
        context.setServer(server);

        try {
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
