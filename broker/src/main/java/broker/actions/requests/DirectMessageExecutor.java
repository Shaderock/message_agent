package broker.actions.requests;

import broker.Context;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.DirectMessageRequestPayload;
import broker.models.payload.DirectMessageResponsePayload;
import broker.models.protocols.Operation;
import broker.communication.MessageGenerator;

public class DirectMessageExecutor extends ProtocolTaskExecutor {

    public DirectMessageExecutor(DirectMessageRequestPayload payload) {
        super(payload);
    }

    @Override
    public void execute(Module module) {
        Context context = Context.getInstance();
        DirectMessageRequestPayload directMessageRequestPayload = (DirectMessageRequestPayload) getPayload();
        MessageGenerator messageGenerator = new MessageGenerator();

        Module receiver = null;
        for (PortData portsDatum : context.getPortsData()) {
            for (Module portsDatumModule : portsDatum.getModules()) {
                if (portsDatumModule.getId() == directMessageRequestPayload.getIdReceiver()) {
                    receiver = portsDatumModule;
                    break;
                }
            }
        }

        DirectMessageResponsePayload directMessageResponsePayload = new DirectMessageResponsePayload();
        directMessageResponsePayload.setIdSender(module.getId());
        directMessageResponsePayload.setCommand(directMessageRequestPayload.getCommand());
        directMessageResponsePayload.setInfoBlock(directMessageRequestPayload.getInfoBlock());

        if (receiver != null) {
            messageGenerator.sendMessage(Operation.DIRECT_MESSAGE, directMessageResponsePayload, receiver.getOut());
        }

        // todo what if wrong id...
    }
}
