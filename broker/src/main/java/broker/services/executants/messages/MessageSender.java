package broker.services.executants.messages;

import broker.models.Module;
import broker.utils.ModuleRemover;
import io.grpc.StatusRuntimeException;
import proto.module.MessageRequest;
import proto.module.ModuleServiceGrpc;

import java.util.concurrent.TimeUnit;

public class MessageSender {

    public static void sendMessage(Module module,
                                   ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub,
                                   MessageRequest.Builder notifyRequestToModule,
                                   proto.broker.MessageRequest notifyRequestFromModule) {
        notifyRequestToModule.setMessage(notifyRequestFromModule.getMessage());
        try {
            proto.module.EmptyMessage response =
                    moduleServiceStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                            .receiveMessage(notifyRequestToModule.build());
            System.out.println("SENT: " + response.toString());
        }
        catch (StatusRuntimeException e) {
            System.out.println("Time for response has exceeded");
            ModuleRemover.removeModule(module);
        }
    }
}
