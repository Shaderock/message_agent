package broker.grpc.services.executants;

import broker.exceptions.SelfSubscribeException;
import broker.models.Module;
import broker.models.PortData;
import broker.models.payload.Code;
import broker.models.payload.CodeIdsPayload;
import broker.models.payload.CodePayload;
import broker.models.protocols.Operation;
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
        ArrayList<Integer> idsToCheck = new ArrayList<>();

//        for (int id : idsPayload.getIds()) {
//            idsToCheck.add(id);
//        }
//        ArrayList<Integer> idsToCheckTmp = new ArrayList<>(idsToCheck);
//
//        ArrayList<Integer> checkedIds = new ArrayList<>();
//
//        try {
//            for (PortData portsDatum : context.getPortsData()) {
//                for (Module portsDatumModule : portsDatum.getModules()) {
//                    for (Integer id : idsToCheck) {
//                        if (id == module.getId()) {
//                            throw new SelfSubscribeException("selfSubscribeDetected");
//                        } else {
//                            if (portsDatumModule.getId() == id) {
//                                checkedIds.add(id);
//                                idsToCheckTmp.remove(id);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        catch (SelfSubscribeException e) {
//            messageGenerator.sendTCPMessage(Operation.SUBSCRIBE,
//                    new CodePayload(Code.SELF_SUBSCRIBE), module);
//            return;
//        }
//
//        module.setNotifiersIds(checkedIds);
//
//        if (idsToCheckTmp.size() > 0) {
//            messageGenerator.sendTCPMessage(Operation.SUBSCRIBE,
//                    new CodeIdsPayload(Code.MODULE_DOES_NOT_EXIST, idsToCheck),
//                    module);
//        } else {
//            messageGenerator.sendTCPMessage(Operation.SUBSCRIBE, new CodePayload(Code.OK), module);
//        }
    }
}
