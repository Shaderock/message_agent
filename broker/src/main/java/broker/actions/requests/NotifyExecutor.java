package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.Payload;

public class NotifyExecutor extends ProtocolTaskExecutor {
    public NotifyExecutor(Payload payload) { super(payload); }

    @Override
    public void execute(Module module) {

    }
}
