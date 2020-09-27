package broker.grpc.services.executants;

import broker.Context;
import broker.grpc.GrpcModule;
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
        sendModulesList(request, responseObserver);
    }

    private void sendModulesList(EmptyIdRequest request, StreamObserver<GetModulesResponse> responseObserver) {
        Context context = Context.getInstance();
        int idRequester = (int) request.getIdRequester();

        GetModulesResponse.Builder response = GetModulesResponse.newBuilder();

        for (GrpcModule grpcModule : context.getGrpcModules()) {
            if (grpcModule.getId() != idRequester) {

            }
        }
    }
}
