package broker.grpc.services.executants;

import broker.Context;
import broker.grpc.GrpcModule;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import proto.broker.SubscribeRequest;
import proto.broker.SubscribeResponse;

import java.util.ArrayList;

@RequiredArgsConstructor
public class SubscribeExecutant extends Executant {
    private final SubscribeRequest request;
    private final StreamObserver<SubscribeResponse> responseObserver;

    @Override
    public void execute() {
        subscribe(request, responseObserver);
    }

    private void subscribe(SubscribeRequest request, StreamObserver<SubscribeResponse> responseObserver) {
        long idRequester = request.getIdSubscriber();

        ArrayList<Long> idsToCheck = new ArrayList<>(request.getIdList());
        ArrayList<Long> idsToCheckTmp = new ArrayList<>(idsToCheck);
        ArrayList<Long> checkedIds = new ArrayList<>();

        Context context = Context.getInstance();

        for (GrpcModule grpcModule : context.getGrpcModules()) {
            for (Long id : idsToCheck) {
                if (id != idRequester) {
                    if (grpcModule.getId() == id) {
                        checkedIds.add(id);
                        idsToCheckTmp.remove(id);
                    }
                }
            }
        }

        SubscribeResponse.Builder response = SubscribeResponse.newBuilder();

        if (idsToCheckTmp.size() > 0) {
            response.setOk(false);
            response.addAllWrongId(idsToCheckTmp);
        } else {
            response.setOk(true);
            for (GrpcModule grpcModule : context.getGrpcModules()) {
                if (grpcModule.getId() == idRequester) {
                    grpcModule.setNotifiersId(checkedIds);
                }
            }
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
