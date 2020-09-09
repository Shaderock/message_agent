package broker.actions;

import broker.models.payload.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.PrintWriter;
import java.net.Socket;

@Data
@AllArgsConstructor
public abstract class ProtocolTaskExecutor {
    private Payload payload;

    abstract void execute(Socket moduleSocket, PrintWriter moduleOutput);
}
