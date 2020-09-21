package broker.actions.requests;

import broker.actions.not_requests.ModuleRemover;
import broker.models.Module;
import broker.models.payload.Payload;

public class CloseConnectionExecutor extends ProtocolTaskExecutor {
    public CloseConnectionExecutor(Payload payload) {
        super(payload);
    }

    public void execute(Module module) {
        ModuleRemover moduleRemover = new ModuleRemover();
        moduleRemover.removeModuleFromStorage(module);
    }
}
