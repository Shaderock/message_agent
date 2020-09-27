package broker.services.executants.messages;

import broker.Context;
import broker.exceptions.ModuleDoesNotExistException;
import broker.models.Module;
import broker.services.executants.Executant;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyMessage;
import proto.module.MessageRequest;
import proto.module.ModuleServiceGrpc;

@RequiredArgsConstructor
public class DirectMessageExecutant extends Executant {
    private final proto.broker.MessageRequest requestFromModule;
    private final StreamObserver<EmptyMessage> responseObserver;

    @Override
    public void execute() {
        sendDirectMessage();
    }

    private void sendDirectMessage() {
        Context context = Context.getInstance();
        long idRequester = requestFromModule.getIdRequester();
        long idReceiver = requestFromModule.getIdReceiver();
        Module receiver;

        try {
            context.findModuleById(idRequester);
            receiver = context.findModuleById(idReceiver);
        }
        catch (ModuleDoesNotExistException e) {
            System.out.println("Module with id=" + e.getId() + " does not exist");
            responseObserver.onNext(EmptyMessage.newBuilder().build());
            responseObserver.onCompleted();
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(receiver.getIp(), receiver.getPort()).usePlaintext().build();
        ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                ModuleServiceGrpc.newBlockingStub(channel);

        proto.module.MessageRequest.Builder request = MessageRequest.newBuilder();
        request.setIdSender(idRequester);
        setMessageToRequest(receiver, moduleServiceStub, request);

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }

    private void setMessageToRequest(Module receiver,
                                     ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub,
                                     MessageRequest.Builder requestToModule) {
        MessageSender.sendMessage(receiver, moduleServiceStub,
                requestToModule, this.requestFromModule);
    }

}
