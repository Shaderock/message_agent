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
import proto.broker.MessageRequest;
import proto.module.ModuleServiceGrpc;

@RequiredArgsConstructor
public class NotifyExecutant extends Executant {
    private final MessageRequest requestFromModule;
    private final StreamObserver<EmptyMessage> responseObserver;

    @Override
    public void execute() {
        notifyExecutant();
    }

    private void notifyExecutant() {
        long idRequester = requestFromModule.getIdRequester();
        Context context = Context.getInstance();

        Module foundModule;
        try {
            foundModule = context.findModuleById(idRequester);
        }
        catch (ModuleDoesNotExistException e) {
            System.out.println("Module with id=" + e.getId() + " does not exist");
            responseObserver.onNext(EmptyMessage.newBuilder().build());
            responseObserver.onCompleted();
            return;
        }

        for (Module module : context.getModules()) {
            for (Long notifierId : foundModule.getNotifiersId()) {
                if (notifierId == idRequester) {
                    ManagedChannel channel = ManagedChannelBuilder
                            .forAddress(module.getIp(), module.getPort()).usePlaintext().build();
                    ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                            ModuleServiceGrpc.newBlockingStub(channel);

                    proto.module.MessageRequest.Builder request = proto.module.MessageRequest.newBuilder();
                    request.setIdSender(module.getId());
                    MessageSender.sendMessage(module, moduleServiceStub, request, this.requestFromModule);
                }
            }
        }

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }

}
