package broker.grpc.services.executants;

import broker.Context;
import broker.grpc.GrpcModule;
import broker.grpc.exceptions.ModuleDoesNotExistException;
import broker.grpc.utils.ModuleRemover;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyIdRequest;
import proto.broker.EmptyMessage;

@RequiredArgsConstructor
public class CloseConnectionExecutant extends Executant {

    private final EmptyIdRequest request;
    private final StreamObserver<EmptyMessage> responseObserver;

    @Override
    public void execute() {
        closeConnection();
    }

    private void closeConnection() {
        try {
            GrpcModule module = Context.getInstance().findModuleById(request.getIdRequester());
            ModuleRemover.removeModule(module);
        }
        catch (ModuleDoesNotExistException e) {
            System.out.println("Can not close connection for id=" + request.getIdRequester());
        }

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }
}