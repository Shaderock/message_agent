package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.DirectMessageRequestPayload;

public class SubscribeExecutor extends ProtocolTaskExecutor {

    //todo: котя хочет это сделать и порадовать Димарика, но без понятия как....
    public SubscribeExecutor(DirectMessageRequestPayload payload) {
        super(payload);
    }

    @Override
    public void execute(Module module) {
        // todo: лапками клац клац и метод создастся.
    }
}
