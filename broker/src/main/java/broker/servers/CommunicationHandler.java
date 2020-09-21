package broker.servers;

import broker.actions.requests.ProtocolTaskExecutor;
import broker.actions.requests.ProtocolTaskExecutorFactory;
import broker.communication.MessageListener;
import broker.communication.ResponseGenerator;
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

public class CommunicationHandler extends Thread {
    private final Module module;
    @Setter
    private boolean isWorking = true;

    public CommunicationHandler(Module justConnectedModule) {
        module = justConnectedModule;
    }

    @Override
    public void run() {
        super.run();
        notifyAboutNewModuleConnected();
        acceptMessages();
    }

    private void acceptMessages() {
        DataInputStream in = module.getIn();
        PrintWriter out = module.getOut();

        MessageListener messageListener = new MessageListener();
        ProtocolTaskExecutorFactory protocolTaskExecutorFactory = new ProtocolTaskExecutorFactory();
        ResponseGenerator responseGenerator = new ResponseGenerator();
        ProtocolTaskExecutor taskExecutor;

        while (isWorking) {
            try {
                String request = messageListener.listen(in);
                taskExecutor = protocolTaskExecutorFactory.createProtocolTaskExecutor(request);
                taskExecutor.execute(module);
            }
            catch (UnsupportableOperationException | OperationNotPresentException e) {
                responseGenerator.sendResponse(e.getOperation(),
                        new CodePayload(Code.UNSUPPORTABLE_OPERATION), out);
            }
            catch (WrongPayloadSchemeException e) { // todo get the operation from the requests
                responseGenerator.sendResponse(e.getOperation(),
                        new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME), out);
            }
            catch (IOException e) {
                // todo handle module disconnect exception
            }
        }
    }

    private void notifyAboutNewModuleConnected() {
        // todo
    }
}
