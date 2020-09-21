
package broker;

import broker.models.PortData;
import broker.servers.HandshakeServer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Context {
    private static Context instance;

    public final int HANDSHAKE_PORT = 17001;
    public final int COMMUNICATION_PORT = 17002;
    public final int MAX_COMMUNICATION_PORTS = 1;

    @Getter
    @Setter
    private int nextModuleId = 0;

    @Getter
    private final ArrayList<PortData> portsData;

    @Getter
    private final ArrayList<HandshakeServer> handshakeServers;

    public final int MAX_SOCKETS_PER_PORT = 50;

    private Context() {
        portsData = new ArrayList<>();
        handshakeServers = new ArrayList<>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }
}
