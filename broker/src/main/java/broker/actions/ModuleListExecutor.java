package broker.actions;

import broker.models.payload.Payload;

import java.io.PrintWriter;
import java.net.Socket;

public class ModuleListExecutor extends ProtocolTaskExecutor {
    public ModuleListExecutor(Payload payload) {
        super(payload);
    }

    public void execute(Socket moduleSocket, PrintWriter moduleOutput) {
        // todo
    }
}
