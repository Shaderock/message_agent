package broker.actions.requests;

import broker.models.Module;

import broker.models.payload.NotifyRequestPayload;
import broker.models.payload.NotifyResponsePayload;

public class NotifyExecutor extends ProtocolTaskExecutor {

    public NotifyExecutor(NotifyRequestPayload payload) { super(payload); }

    @Override
    public void execute(Module module) {

        NotifyRequestPayload notifyRequestPayload = (NotifyRequestPayload) getPayload();

        NotifyResponsePayload notifyResponsePayload = new NotifyResponsePayload();
        NotifyResponsePayload.setIdSender(module.getId());
        NotifyResponsePayload.setCommand(notifyResponsePayload.getCommand());
        NotifyResponsePayload.setInfoBlock(notifyResponsePayload.getInfoBlock());

    }
}
