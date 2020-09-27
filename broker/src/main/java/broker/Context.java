
package broker;

import broker.grpc.GrpcModule;
import broker.models.PortData;
import broker.servers.Worker;
import io.grpc.Server;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Context {
    public static final int MAX_SOCKETS_PER_PORT = 50;
    private static Context instance;

    public final int UDP_BROKER_LISTENED_PORT = 16002;

    public final int GRPS_SERVER_PORT = 17001;
    public final int GRPS_COMMUNICATION_PORT = 17002;
    public final int MAX_COMMUNICATION_PORTS = 1;

    public boolean APP_IS_SHUT_DOWN;

    @Getter
    private final ArrayList<Worker> workers;

    @Getter
    private final ArrayList<GrpcModule> grpcModules;

    @Getter
    @Setter
    private Server server;

    @Getter
    @Setter
    private int nextModuleId = 0;

    @Getter
    private final ArrayList<PortData> portsData;

    private Context() {
        portsData = new ArrayList<>();
        workers = new ArrayList<>();
        grpcModules = new ArrayList<>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }
}
