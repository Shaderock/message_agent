package broker.grpc.services.executants;

import broker.Context;
import broker.grpc.GrpcModule;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyIdRequest;
import proto.broker.GetModulesResponse;
import proto.broker.Module;

@RequiredArgsConstructor
public class GetModulesExecutant extends Executant {
    private final EmptyIdRequest request;
    private final StreamObserver<GetModulesResponse> responseObserver;

    @Override
    public void execute() {
        sendModulesList();
    }

    private void sendModulesList() {
        Context context = Context.getInstance();
        int idRequester = (int) request.getIdRequester();

        GetModulesResponse.Builder response = GetModulesResponse.newBuilder();

        for (GrpcModule grpcModule : context.getGrpcModules()) {
            if (grpcModule.getId() != idRequester) {
                Module.Builder module = Module.newBuilder();
                module.setType(grpcModule.getType().getName());
                module.setId(grpcModule.getId());
                response.addModule(module.build());
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
