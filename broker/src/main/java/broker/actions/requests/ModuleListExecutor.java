package broker.actions.requests;

import broker.Context;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.Code;
import broker.models.payload.ModuleListPayload;
import broker.models.payload.Payload;
import broker.models.protocols.Operation;
import broker.utils.ResponseGenerator;

public class ModuleListExecutor extends ProtocolTaskExecutor {
    public ModuleListExecutor(Payload payload) {
        super(payload);
    }

    public void execute(Module module) {
        Context context = Context.getInstance();
        ModuleListPayload moduleListPayload = new ModuleListPayload();
        ResponseGenerator responseGenerator = new ResponseGenerator();

        for (PortData portsDatum : context.getPortsData()) {
            for (Module portsDatumModule : portsDatum.getModules()) {
                if (portsDatumModule.getId() != module.getId()) {
                    moduleListPayload.getModules().add(portsDatumModule);
                }
            }
        }

        moduleListPayload.setCode(Code.OK);
        responseGenerator.sendResponse(Operation.GET_MODULES, moduleListPayload, module.getOut());
    }
}
