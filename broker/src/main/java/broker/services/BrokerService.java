package broker.services;

import broker.services.executants.*;
import broker.services.executants.messages.DirectMessageExecutant;
import broker.services.executants.messages.NotifyExecutant;
import io.grpc.stub.StreamObserver;
import proto.broker.*;

public class BrokerService extends BrokerServiceGrpc.BrokerServiceImplBase {

    @Override
    public void handshake(HandshakeRequest request, StreamObserver<HandshakeResponse> responseObserver) {
        System.out.println("RECEIVED: " + request.toString());
        HandshakeExecutant handshakeExecutant = new HandshakeExecutant(request, responseObserver);
        handshakeExecutant.execute();
    }

    @Override
    public void getModules(EmptyIdRequest request, StreamObserver<GetModulesResponse> responseObserver) {
        System.out.println("RECEIVED: " + request.toString());
        GetModulesExecutant getModulesExecutant = new GetModulesExecutant(request, responseObserver);
        getModulesExecutant.execute();
    }

    @Override
    public void subscribe(SubscribeRequest request, StreamObserver<SubscribeResponse> responseObserver) {
        System.out.println("RECEIVED: " + request.toString());
        SubscribeExecutant subscribeExecutant = new SubscribeExecutant(request, responseObserver);
        subscribeExecutant.execute();
    }

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<EmptyMessage> responseObserver) {
        System.out.println("RECEIVED: " + request.toString());
        IExecutant executant;
        if (request.hasIdReceiver()) {
            executant = new DirectMessageExecutant(request, responseObserver);
        } else {
            executant = new NotifyExecutant(request, responseObserver);
        }
        executant.execute();
    }

    @Override
    public void close(EmptyIdRequest request, StreamObserver<EmptyMessage> responseObserver) {
        System.out.println("RECEIVED: " + request.toString());
        CloseConnectionExecutant closeConnectionExecutant =
                new CloseConnectionExecutant(request, responseObserver);
        closeConnectionExecutant.execute();
    }
}
