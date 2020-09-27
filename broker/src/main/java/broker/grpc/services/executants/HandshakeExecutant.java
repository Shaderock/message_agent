package broker.grpc.services.executants;

import broker.Context;
import broker.grpc.GrpcModule;
import broker.models.payload.Type;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.HandshakeRequest;
import proto.broker.HandshakeResponse;

@RequiredArgsConstructor
public class HandshakeExecutant extends Executant {
    private final HandshakeRequest request;
    private final StreamObserver<HandshakeResponse> responseObserver;

    @Override
    public void execute() {
        handshake(request, responseObserver);
    }

    private synchronized static void handshake(HandshakeRequest request,
                                               StreamObserver<HandshakeResponse> responseObserver) {
        Context context = Context.getInstance();
        HandshakeResponse.Builder response = HandshakeResponse.newBuilder();
        Type type;

        try {
            type = Type.valueOf(request.getType());
        }
        catch (IllegalArgumentException | NullPointerException e) {
            response.setOk(false);
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
            return;
        }

        int connections = 0;
        for (GrpcModule grpcModule : context.getGrpcModules()) {
            if (grpcModule.getType() == type) {
                connections++;
            }
        }
        if (connections >= type.getMaxConnections()) {
            response.setOk(false);
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }

        int nextModuleId = context.getNextModuleId();
        GrpcModule grpcModule = new GrpcModule(nextModuleId, type,
                request.getIp(), (int) request.getPort());
        context.setNextModuleId(nextModuleId + 1);
        context.getGrpcModules().add(grpcModule);

        response.setGivenId(nextModuleId);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
