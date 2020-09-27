
package broker;

import broker.models.GrpcModule;
import broker.exceptions.ModuleDoesNotExistException;
import broker.servers.Worker;
import io.grpc.Server;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Context {
    private static Context instance;

    public final int UDP_BROKER_LISTENED_PORT = 16002;
    public final int GRPS_SERVER_PORT = 17001;

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

    private Context() {
        workers = new ArrayList<>();
        grpcModules = new ArrayList<>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public GrpcModule findModuleById(long id) throws ModuleDoesNotExistException {
        for (GrpcModule grpcModule : getGrpcModules()) {
            if (grpcModule.getId() == id) {
                return grpcModule;
            }
        }
        throw new ModuleDoesNotExistException("No module with id=" + id, id);
    }
}
