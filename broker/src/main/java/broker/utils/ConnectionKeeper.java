package broker.utils;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.models.Module;
import broker.models.PortData;
import broker.models.protocols.Operation;
import broker.servers.Finishable;

public class ConnectionKeeper implements Finishable {
    private boolean isRunning = true;

    @SuppressWarnings("BusyWait")
    public void keepConnection() {
        Context context = Context.getInstance();
        MessageGenerator messageGenerator = new MessageGenerator();

        while (isRunning) {
            try {
                Thread.sleep(15000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (PortData portsDatum : context.getPortsData()) {
                for (Module module : portsDatum.getModules()) {
                    messageGenerator.sendMessage(Operation.KEEP_ALIVE, null, module);
                }
            }
        }
    }

    @Override
    public void finish() {
        isRunning = false;
    }
}
