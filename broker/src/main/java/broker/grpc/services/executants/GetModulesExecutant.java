package broker.grpc.services.executants;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.EmptyMessage;
import proto.broker.GetModulesResponse;

@RequiredArgsConstructor
public class GetModulesExecutant extends Executant {
    private final EmptyMessage request;
    private final StreamObserver<GetModulesResponse> responseObserver;

    @Override
    public void run() {
        sendModulesList(request, responseObserver);
    }

    private void sendModulesList(EmptyMessage request, StreamObserver<GetModulesResponse> responseObserver) {
        // todo
    }
}
