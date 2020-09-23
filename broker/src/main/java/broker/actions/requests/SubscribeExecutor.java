package broker.actions.requests;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.exceptions.SelfSubscribeException;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.payload.SubscribePayloadIDs;
import broker.models.payload.SubscribePayloadNonExisting;
import broker.models.protocols.Operation;

import java.util.ArrayList;

public class SubscribeExecutor extends ProtocolTaskExecutor {

    public SubscribeExecutor(SubscribePayloadIDs payload) {
        super(payload);
    }

    @Override
    public void execute(Module module) {
        SubscribePayloadIDs subscribePayloadIDs = (SubscribePayloadIDs) getPayload();
        Context context = Context.getInstance();
        MessageGenerator messageGenerator = new MessageGenerator();

        ArrayList<Integer> idsToCheck = new ArrayList<>();
        for (int id : subscribePayloadIDs.getModulesIdsToSubscribeOn()) {
            idsToCheck.add(id);
        }
        ArrayList<Integer> checkedIds = new ArrayList<>();

        try {
            for (PortData portsDatum : context.getPortsData()) {
                for (Module portsDatumModule : portsDatum.getModules()) {
                    for (Integer id : idsToCheck) {
                        if (id == module.getId()) {
                            throw new SelfSubscribeException("selfSubscribeDetected");
                        } else {
                            if (portsDatumModule.getId() == id) {
                                checkedIds.add(id);
                                idsToCheck.remove(id);
                            }
                        }
                    }
                }
            }
        }
        catch (SelfSubscribeException e) {
            messageGenerator.sendMessage(Operation.SUBSCRIBE,
                    new CodePayload(Code.SELF_SUBSCRIBE), module.getOut());
            return;
        }

        if (idsToCheck.size() > 0) {
            messageGenerator.sendMessage(Operation.SUBSCRIBE,
                    new SubscribePayloadNonExisting(Code.MODULE_DOES_NOT_EXIST, idsToCheck),
                    module.getOut());
        } else {
            module.setNotifiersIds(checkedIds);
            messageGenerator.sendMessage(Operation.SUBSCRIBE, new CodePayload(Code.OK), module.getOut());
        }
    }
}
