package broker.grpc.services;

import io.grpc.Grpc;
import io.grpc.ServerCall;
import io.grpc.ServerStreamTracer;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import proto.broker.*;
//todo
public class BrokerService extends BrokerServiceGrpc.BrokerServiceImplBase {

    @Override
    public void handshake(HandshakeRequest request, StreamObserver<HandshakeResponse> responseObserver) {

    }

    @Override
    public void getModules(EmptyMessage request, StreamObserver<GetModulesResponse> responseObserver) {
        super.getModules(request, responseObserver);
    }

    @Override
    public void subscribe(SubscribeRequest request, StreamObserver<SubscribeResponse> responseObserver) {
        super.subscribe(request, responseObserver);
    }

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<EmptyMessage> responseObserver) {
        super.sendMessage(request, responseObserver);
    }

    @Override
    public void close(EmptyMessage request, StreamObserver<EmptyMessage> responseObserver) {
        super.close(request, responseObserver);
    }
}
