
package broker;

import broker.models.PortData;
import broker.servers.Finishable;
import broker.servers.HandshakeServer;
import broker.utils.ConnectionKeeper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Context {
    private static Context instance;

    public final int BROKER_LISTENED_PORT = 16002;
    public final int HANDSHAKE_PORT = 17001;
    public final int COMMUNICATION_PORT = 17002;
    public final int MAX_COMMUNICATION_PORTS = 1;

    @Getter
    private final ArrayList<Finishable> workers;

    @Getter
    @Setter
    private int nextModuleId = 0;

    @Getter
    private final ArrayList<PortData> portsData;

    public final int MAX_SOCKETS_PER_PORT = 50;

    private Context() {
        portsData = new ArrayList<>();
        workers = new ArrayList<>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }
}
