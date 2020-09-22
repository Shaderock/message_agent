package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.SubscribePayloadIDs;

public class SubscribeExecutor extends ProtocolTaskExecutor {

    public SubscribeExecutor(SubscribePayloadIDs payload) {
        super(payload);
    }

    @Override
    public void execute(Module module) {
        // todo: лапками клац клац и метод создастся.
    }
}
