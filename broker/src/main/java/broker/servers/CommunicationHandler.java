package broker.servers;

import broker.actions.not_requests.ModulesConnectionNotifier;
import broker.models.Module;

public class CommunicationHandler
        extends Thread {

    private final Module module;
    private ReceivedMessageHandler receivedMessageHandler;

    private final ModulesConnectionNotifier modulesConnectionNotifier =
            new ModulesConnectionNotifier();

    public CommunicationHandler(Module justConnectedModule) {
        module = justConnectedModule;
    }

    @Override
    public void run() {
        receivedMessageHandler = new ReceivedMessageHandler(module);
        receivedMessageHandler.start();
        modulesConnectionNotifier.notifyAboutNewModuleConnected(module);
    }

    @Override
    public void interrupt() {
        if (receivedMessageHandler != null) {
            receivedMessageHandler.interrupt();
        }
        super.interrupt();
        System.out.println("CommunicationHandler finished its work");
    }
}
