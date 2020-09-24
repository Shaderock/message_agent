package broker.actions.requests;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.models.Module;

import broker.models.PortData;
import broker.models.payload.NotifyRequestPayload;
import broker.models.payload.NotifyResponsePayload;
import broker.models.protocols.Operation;

public class NotifyExecutor extends ProtocolTaskExecutor {

    public NotifyExecutor(NotifyRequestPayload payload) { super(payload); }

    @Override
    public void execute(Module module) {
        NotifyRequestPayload notifyRequestPayload = (NotifyRequestPayload) getPayload();
        NotifyResponsePayload notifyResponsePayload = new NotifyResponsePayload();
        notifyResponsePayload.setIdSender(module.getId());
        notifyResponsePayload.setCommand(notifyRequestPayload.getCommand());
        notifyResponsePayload.setInfoBlock(notifyRequestPayload.getInfoBlock());

        Context context = Context.getInstance();
        MessageGenerator messageGenerator = new MessageGenerator();

        for (PortData portsDatum : context.getPortsData()) {
            for (Module portsDatumModule : portsDatum.getModules()) {
                for (Integer notifierId : portsDatumModule.getNotifiersIds()) {
                    if (notifierId == module.getId()){
                        messageGenerator.sendMessage(Operation.NOTIFY,
                                notifyResponsePayload, portsDatumModule);
                    }
                }
            }
        }

        // todo if module was deleted ...?
    }
}
