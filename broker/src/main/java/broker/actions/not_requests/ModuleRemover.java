package broker.actions.not_requests;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.models.Module;
import broker.models.PortData;
import broker.models.protocols.Operation;

import java.io.IOException;

public class ModuleRemover {
    public synchronized static void removeModuleFromStorage(Module module) {
        Context context = Context.getInstance();
        for (PortData portsDatum : context.getPortsData()) {
            if (portsDatum.getModules().remove(module)) {
                disableModule(module, context);
                break;
            }
        }
    }

    private static synchronized void disableModule(Module module, Context context) {
        MessageGenerator messageGenerator = new MessageGenerator();
        ModulesConnectionNotifier modulesConnectionNotifier = new ModulesConnectionNotifier();

        module.setConnected(false);

        if (context.APP_IS_SHUT_DOWN) {
            messageGenerator.sendTCPMessage(Operation.CLOSE, null, module);
        } else {
            modulesConnectionNotifier.notifyAboutModuleDisconnected(module);
        }

        module.getOut().close();
        try {
            module.getIn().close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                module.getSocket().close();
                System.out.println("Module with type " + module.getType().name() +
                        " with id=" + module.getId() + " has been disabled and removed from storage");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
