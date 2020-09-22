package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.NotifyPayload;
import broker.models.payload.Payload;

public class NotifyExecutor extends ProtocolTaskExecutor {

    public NotifyExecutor(NotifyPayload payload) { super(payload); }

    @Override
    public void execute(Module module) {

    }
}
