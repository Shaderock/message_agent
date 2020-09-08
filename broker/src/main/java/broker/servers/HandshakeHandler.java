package broker.servers;

import broker.actions.HandshakeExecutor;
import broker.actions.ProtocolTaskExecutorFactory;
import broker.exceptions.WrongProtocolSyntaxException;
import broker.models.protocols.Protocol;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandshakeHandler extends Thread {
    private final Socket moduleSocket;

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

        String request = in.readLine();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Protocol protocol = objectMapper.readValue(request, Protocol.class);
            ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
            HandshakeExecutor handshakeExecutor =
                    (HandshakeExecutor) protocolTaskExecutorFactory.createProtocolTaskExecutor(protocol);
            handshakeExecutor.execute(out);
        }
        catch (JsonMappingException e) {
            System.out.println("Could not deserialize JSON");
        }
        catch (WrongProtocolSyntaxException e) {
            System.out.println("Wrong protocol syntax");
        }
    }
}
