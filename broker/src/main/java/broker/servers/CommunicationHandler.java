package broker.servers;

import broker.actions.not_requests.ModuleRemover;
import broker.actions.not_requests.ModulesConnectionNotifier;
import broker.actions.requests.ProtocolTaskExecutor;
import broker.actions.requests.ProtocolTaskExecutorFactory;
import broker.communication.MessageGenerator;
import broker.communication.MessageListener;
import broker.exceptions.OperationNotPresentException;
import broker.exceptions.UnsupportableOperationException;
import broker.exceptions.WrongPayloadSchemeException;
import broker.models.Module;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CommunicationHandler
        extends Thread
        implements Finishable {
    private final Module module;
    @Setter
    private boolean isWorking = true;

    private final ModulesConnectionNotifier modulesConnectionNotifier =
            new ModulesConnectionNotifier();
    private final ModuleRemover moduleRemover = new ModuleRemover();

    public CommunicationHandler(Module justConnectedModule) {
        module = justConnectedModule;
    }

    @Override
    public void run() {
        super.run();
        acceptMessages();
        modulesConnectionNotifier.notifyAboutNewModuleConnected(module);
    }

    private void acceptMessages() {
        DataInputStream in = module.getIn();
        PrintWriter out = module.getOut();

        MessageListener messageListener = new MessageListener();
        ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
        MessageGenerator messageGenerator = new MessageGenerator();
        ProtocolTaskExecutor taskExecutor;

        while (isWorking) {
            try {
                String request = messageListener.listen(in);
                taskExecutor = protocolTaskExecutorFactory.createProtocolTaskExecutor(request);
                taskExecutor.execute(module);
            }
            catch (UnsupportableOperationException | OperationNotPresentException e) {
                messageGenerator.sendMessage(e.getOperation(),
                        new CodePayload(Code.UNSUPPORTABLE_OPERATION), out);
            }
            catch (WrongPayloadSchemeException e) {
                messageGenerator.sendMessage(e.getOperation(),
                        new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME), out);
            }
            catch (IOException e) {
                moduleRemover.removeModuleFromStorage(module);
                modulesConnectionNotifier.notifyAboutModuleDisconnected(module);
            }
        }
    }

    @Override
    public void finish() {
        isWorking = false;
    }
}
