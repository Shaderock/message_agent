package broker.actions.not_requests;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.TypeIdPayload;
import broker.models.protocols.Operation;

public class ModulesConnectionNotifier {
    private final MessageGenerator messageGenerator = new MessageGenerator();

    public void notifyAboutNewModuleConnected(Module module) {
        notify(module, Operation.WELCOME);
    }

    public void notifyAboutModuleDisconnected(Module module) {
        notify(module, Operation.GOOD_BYE);
    }

    private void notify(Module module, Operation operation) {
        Context context = Context.getInstance();
        for (PortData portsDatum : context.getPortsData()) {
            for (Module portsDatumModule : portsDatum.getModules()) {
                if (module.getId() != portsDatumModule.getId()) {
                    messageGenerator.sendMessage(operation,
                            new TypeIdPayload(module.getType(), module.getId()),
                            portsDatumModule.getOut());
                }
            }
        }
    }
}
