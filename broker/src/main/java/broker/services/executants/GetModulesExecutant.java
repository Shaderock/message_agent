package broker.services.executants;

import broker.Context;
import broker.models.Module;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyIdRequest;
import proto.broker.GetModulesResponse;

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

        for (Module grpcModule : context.getModules()) {
            if (grpcModule.getId() != idRequester) {
                proto.broker.Module.Builder module = proto.broker.Module.newBuilder();
                module.setType(grpcModule.getType().getName());
                module.setId(grpcModule.getId());
                response.addModule(module.build());
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
