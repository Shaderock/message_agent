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
            MessageRequest request = notifyRequestToModule.build();
            proto.module.EmptyMessage response =
                    moduleServiceStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                            .receiveMessage(request);
            System.out.println("gRPC SENT to id=" + module.getId() + ": SIMPLE MESSAGE" + "\n" + request.toString());
        }
        catch (StatusRuntimeException e) {
            System.out.println("Time for response has exceeded");
            ModuleRemover.removeModule(module);
        }
    }
}
