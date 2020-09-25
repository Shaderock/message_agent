package broker.servers;

import broker.actions.not_requests.ModulesConnectionNotifier;
import broker.models.Module;

public class TCPCommunicationHandler
        extends Thread {

    private final Module module;
    private TCPReceivedMessageHandler TCPReceivedMessageHandler;

    private final ModulesConnectionNotifier modulesConnectionNotifier =
            new ModulesConnectionNotifier();

    public TCPCommunicationHandler(Module justConnectedModule) {
        module = justConnectedModule;
    }

    @Override
    public void run() {
        TCPReceivedMessageHandler = new TCPReceivedMessageHandler(module);
        TCPReceivedMessageHandler.start();
        modulesConnectionNotifier.notifyAboutNewModuleConnected(module);
    }

    @Override
    public void interrupt() {
        if (TCPReceivedMessageHandler != null) {
            TCPReceivedMessageHandler.interrupt();
        }
        super.interrupt();
        System.out.println("CommunicationHandler finished its work");
    }
}
