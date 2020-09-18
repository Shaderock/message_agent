package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.Payload;

public class CloseConnectionExecutor extends ProtocolTaskExecutor {
    public CloseConnectionExecutor(Payload payload) {
        super(payload);
    }

    public void execute(Module module) {
        // todo
    }
}
