package broker.utils;

import broker.Context;
import broker.communication.MessageGenerator;
import broker.models.Module;
import broker.models.PortData;
import broker.models.protocols.Operation;
import broker.servers.Worker;

public class ConnectionKeeper
        extends Worker {
    private boolean isWorking = true;

    @Override
    public void run() {
        keepConnection();
    }

    @SuppressWarnings("BusyWait")
    public void keepConnection() {
        Context context = Context.getInstance();
        MessageGenerator messageGenerator = new MessageGenerator();

        while (isWorking) {
            try {
                Thread.sleep(15000);
            }
            catch (InterruptedException e) {
                System.out.println("Connection keeper sleep interrupted");
            }
            for (PortData portsDatum : context.getPortsData()) {
                for (Module module : portsDatum.getModules()) {
                    messageGenerator.sendMessage(Operation.KEEP_ALIVE, null, module);
                }
            }
        }
    }

    @Override
    public void interrupt() {
        isWorking = false;
        System.out.println("ConnectionKeeper finished its work");
    }
}
