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

    private class MessageHandler extends Thread {
        @Override
        public void run() {
            acceptMessages();
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
                    System.out.print("RECEIVED from id=" + module.getId() + ", message: " + request);
                    taskExecutor = protocolTaskExecutorFactory.createProtocolTaskExecutor(request);
                    taskExecutor.execute(module);
                }
                catch (UnsupportableOperationException | OperationNotPresentException e) {
                    messageGenerator.sendMessage(e.getOperation(),
                            new CodePayload(Code.UNSUPPORTABLE_OPERATION),
                            Module.builder()
                                    .out(out)
                                    .id(-1)
                                    .build());
                }
                catch (WrongPayloadSchemeException e) {
                    messageGenerator.sendMessage(e.getOperation(),
                            new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME),
                            Module.builder()
                                    .out(out)
                                    .id(-1)
                                    .build());
                }
                catch (IOException e) {
                    ModuleRemover.removeModuleFromStorage(module);
                    modulesConnectionNotifier.notifyAboutModuleDisconnected(module);
                }
            }
        }
    }

    private final Module module;
    private MessageHandler messageHandler;

    @Setter
    private boolean isWorking = true;

    private final ModulesConnectionNotifier modulesConnectionNotifier =
            new ModulesConnectionNotifier();

    public CommunicationHandler(Module justConnectedModule) {
        module = justConnectedModule;
    }

    @Override
    public void run() {
        messageHandler = new MessageHandler();
        messageHandler.start();

        modulesConnectionNotifier.notifyAboutNewModuleConnected(module);
    }

    @Override
    public void finish() {
        this.isWorking = false;
        messageHandler.interrupt();
        this.interrupt();
        System.out.println("CommunicationHandler finished its work");
    }
}
