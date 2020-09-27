package broker.grpc.services;

import broker.grpc.services.executants.GetModulesExecutant;
import broker.grpc.services.executants.HandshakeExecutant;
import broker.grpc.services.executants.SubscribeExecutant;
import io.grpc.stub.StreamObserver;
import proto.broker.*;

//todo
public class BrokerService extends BrokerServiceGrpc.BrokerServiceImplBase {

    @Override
    public void handshake(HandshakeRequest request, StreamObserver<HandshakeResponse> responseObserver) {
        HandshakeExecutant handshakeExecutant = new HandshakeExecutant(request, responseObserver);
        handshakeExecutant.execute();
    }

    @Override
    public void getModules(EmptyIdRequest request, StreamObserver<GetModulesResponse> responseObserver) {
        GetModulesExecutant getModulesExecutant = new GetModulesExecutant(request, responseObserver);
        getModulesExecutant.execute();
    }

    @Override
    public void subscribe(SubscribeRequest request, StreamObserver<SubscribeResponse> responseObserver) {
        SubscribeExecutant subscribeExecutant = new SubscribeExecutant(request, responseObserver);
        subscribeExecutant.execute();
    }

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<EmptyMessage> responseObserver) {
        super.sendMessage(request, responseObserver);
    }

    @Override
    public void close(EmptyIdRequest request, StreamObserver<EmptyMessage> responseObserver) {
        super.close(request, responseObserver);
    }
}
