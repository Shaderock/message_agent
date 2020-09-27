package broker.grpc.services.executants;

import broker.Context;
import broker.grpc.GrpcModule;
import broker.grpc.exceptions.ModuleDoesNotExistException;
import broker.grpc.utils.ModuleRemover;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyMessage;
import proto.broker.MessageRequest;
import proto.module.ModuleServiceGrpc;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class NotifyExecutant extends Executant {
    private final MessageRequest request;
    private final StreamObserver<EmptyMessage> responseObserver;

    @Override
    public void execute() {
        notifyExecutant();
    }

    private void notifyExecutant() {
        long idRequester = request.getIdRequester();
        Context context = Context.getInstance();

        GrpcModule foundModule;
        try {
            foundModule = context.findModuleById(idRequester);
        }
        catch (ModuleDoesNotExistException e) {
            System.out.println("Module with id=" + e.getId() + " does not exist");
            responseObserver.onNext(EmptyMessage.newBuilder().build());
            responseObserver.onCompleted();
            return;
        }

        for (GrpcModule module : context.getGrpcModules()) {
            for (Long notifierId : foundModule.getNotifiersId()) {
                if (notifierId == idRequester) {
                    ManagedChannel channel = ManagedChannelBuilder
                            .forAddress(module.getIp(), module.getPort()).usePlaintext().build();
                    ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                            ModuleServiceGrpc.newBlockingStub(channel);

                    proto.module.MessageRequest.Builder request = proto.module.MessageRequest.newBuilder();
                    request.setIdSender(module.getId());
                    request.setMessage(this.request.getMessage());
                    proto.module.EmptyMessage response =
                            moduleServiceStub.withDeadlineAfter(5, TimeUnit.SECONDS)
                                    .receiveMessage(request.build());

                    if (response == null) {
                        ModuleRemover.removeModule(module);
                    }
                }
            }
        }

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }
}
