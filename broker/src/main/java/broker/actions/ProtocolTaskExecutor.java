package broker.actions;

import java.io.PrintWriter;

public interface ProtocolTaskExecutor {
    void execute(PrintWriter moduleSocketOutput);
}
