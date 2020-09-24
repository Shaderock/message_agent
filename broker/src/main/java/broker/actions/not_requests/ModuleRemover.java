package broker.actions.not_requests;

import broker.Context;
import broker.models.Module;
import broker.models.PortData;

import java.io.IOException;

public class ModuleRemover {
    public synchronized static void removeModuleFromStorage(Module module) {
        Context context = Context.getInstance();
        for (PortData portsDatum : context.getPortsData()) {
            portsDatum.getModules().remove(module);
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
