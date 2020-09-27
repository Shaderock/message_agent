
package broker;

import broker.models.Module;
import broker.exceptions.ModuleDoesNotExistException;
import broker.servers.Worker;
import io.grpc.Server;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Context {
    private static Context instance;

    public final int UDP_BROKER_LISTENED_PORT = 16002;
    public final int GRPC_SERVER_PORT = 17001;

    public final int DEADLINE_FOR_RESPONSE_SECONDS = 5;
    public final int SHUTDOWN_TIMEOUT_SECONDS = 5;

    public boolean APP_IS_SHUT_DOWN;

    @Getter
    private final ArrayList<Worker> workers;

    @Getter
    private final ArrayList<Module> modules;

    @Getter
    @Setter
    private Server server;

    @Getter
    @Setter
    private int nextModuleId = 1;

    private Context() {
        workers = new ArrayList<>();
        modules = new ArrayList<>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public Module findModuleById(long id) throws ModuleDoesNotExistException {
        for (Module module : getModules()) {
            if (module.getId() == id) {
                return module;
            }
        }
        throw new ModuleDoesNotExistException("No module with id=" + id, id);
    }
}
