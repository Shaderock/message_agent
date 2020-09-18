package broker.servers;

import broker.actions.requests.ProtocolTaskExecutor;
import broker.actions.requests.ProtocolTaskExecutorFactory;
import broker.exceptions.WrongProtocolSyntaxException;
import broker.models.Module;
import broker.models.payload.Code;
import broker.models.payload.CodePayload;
import broker.models.protocols.Operation;
import broker.utils.MessageListener;
import broker.utils.ResponseGenerator;

import javax.naming.OperationNotSupportedException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CommunicationHandler extends Thread {
    private final Module module;
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

        while (isWorking) {
            try {
                String request = messageListener.listen(in);
                ProtocolTaskExecutor taskExecutor =
                        protocolTaskExecutorFactory.createProtocolTaskExecutor(request);

                taskExecutor.execute(module);
            }
            catch (WrongProtocolSyntaxException e) {
                responseGenerator.sendResponse(Operation.HANDSHAKE,
                        new CodePayload(Code.INCORRECT_PAYLOAD_SCHEME), out);
            }
            catch (OperationNotSupportedException e) {
                responseGenerator.sendResponse(Operation.HANDSHAKE,
                        new CodePayload(Code.UNSUPPORTABLE_OPERATION), out);
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
