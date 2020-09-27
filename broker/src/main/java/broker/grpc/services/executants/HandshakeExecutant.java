package broker.grpc.services.executants;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.HandshakeRequest;
import proto.broker.HandshakeResponse;

@RequiredArgsConstructor
public class HandshakeExecutant extends Executant {
    private final HandshakeRequest request;
    private final StreamObserver<HandshakeResponse> responseObserver;

    @Override
    public void run() {
        handshake(request, responseObserver);
    }

    private synchronized static void handshake(HandshakeRequest request,
                                               StreamObserver<HandshakeResponse> responseObserver) {

    }
}
