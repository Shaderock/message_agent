package broker.servers;

import broker.actions.not_requests.ModuleRemover;
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
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class TCPReceivedMessageHandler
        extends Thread {

    private final Module module;
    private final ProtocolTaskExecutorFactory protocolTaskExecutorFactory =
            new ProtocolTaskExecutorFactory();
    private final MessageGenerator messageGenerator =
            new MessageGenerator();
    private boolean isWorking = true;

    @Override
    public void run() {
        acceptMessages();
    }

    private void acceptMessages() {
        MessageListener messageListener = new MessageListener(module);
        while (isWorking) {
            try {
                String message = messageListener.listen();
                System.out.print("RECEIVED from id=" + module.getId() + ", message: " + message);
                ProtocolTaskExecutor taskExecutor =
                        protocolTaskExecutorFactory.createProtocolTaskExecutor(message);
                taskExecutor.execute(module);
            }
            catch (UnsupportableOperationException | OperationNotPresentException e) {
                messageGenerator.sendTCPMessage(e.getOperation(),
                        new CodePayload(Code.UNSUPPORTABLE_OPERATION),
                        module);
            }
            catch (WrongPayloadSchemeException e) {
                messageGenerator.sendTCPMessage(e.getOperation(),
                        new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME),
                        module);
            }
            catch (IOException e) {
                ModuleRemover.removeModuleFromStorage(module);
                System.out.println("ReceivedMessageHandler is not listening for module with id=" +
                        module.getId() + " anymore");
            }
            catch (InterruptedException e) {
                System.out.println("MessageListener is not listening for a socket anymore");
            }
        }
    }

    @Override
    public void interrupt() {
        isWorking = false;
    }
}
