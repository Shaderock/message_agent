package broker.services.executants;

import broker.Context;
import broker.models.GrpcModule;
import broker.exceptions.ModuleDoesNotExistException;
import broker.utils.ModuleRemover;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyMessage;
import proto.module.MessageRequest;
import proto.module.ModuleServiceGrpc;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class DirectMessageExecutant extends Executant {
    private final proto.broker.MessageRequest request;
    private final StreamObserver<EmptyMessage> responseObserver;

    @Override
    public void execute() {
        sendDirectMessage();
    }

    private void sendDirectMessage() {
        Context context = Context.getInstance();
        long idRequester = request.getIdRequester();
        long idReceiver = request.getIdReceiver();
        GrpcModule receiver;

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
        request.setMessage(this.request.getMessage());
        proto.module.EmptyMessage response = moduleServiceStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                .receiveMessage(request.build());

        if (response == null) {
            ModuleRemover.removeModule(receiver);
        }

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }

}
