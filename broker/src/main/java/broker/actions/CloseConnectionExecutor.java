package broker.actions;

import broker.models.payload.Payload;

import java.io.PrintWriter;
import java.net.Socket;

public class CloseConnectionExecutor extends ProtocolTaskExecutor {
    public CloseConnectionExecutor(Payload payload) {
        super(payload);
    }

    public void execute(Socket moduleSocket, PrintWriter moduleOutput) {
        // todo
    }
}
