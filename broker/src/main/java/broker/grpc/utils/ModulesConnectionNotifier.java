package broker.grpc.utils;

import broker.Context;
import broker.grpc.GrpcModule;
import broker.grpc.exceptions.ModuleDoesNotExistException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.module.GoodByeRequest;
import proto.module.ModuleServiceGrpc;
import proto.module.WelcomeRequest;

import java.util.concurrent.TimeUnit;

public class ModulesConnectionNotifier {
    public void notifyAboutModuleConnected(GrpcModule module) {
        if (moduleDoesNotExist(module)) {
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(module.getIp(), module.getPort()).usePlaintext().build();
        ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                ModuleServiceGrpc.newBlockingStub(channel);

        proto.module.WelcomeRequest.Builder request = WelcomeRequest.newBuilder();
        request.setModule(proto.module.Module.newBuilder()
                .setType(module.getType().getName())
                .setId(module.getId())
                .build());

        proto.module.EmptyMessage response = moduleServiceStub
                .withDeadlineAfter(5, TimeUnit.SECONDS)
                .welcome(request.build());

        if (response == null) {
            ModuleRemover.removeModule(module);
        }
    }

    public void notifyAboutModuleDisconnected(GrpcModule module) {
        if (moduleDoesNotExist(module)) {
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(module.getIp(), module.getPort()).usePlaintext().build();
        ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                ModuleServiceGrpc.newBlockingStub(channel);

        GoodByeRequest.Builder request = GoodByeRequest.newBuilder();
        request.setModule(proto.module.Module.newBuilder()
                .setType(module.getType().getName())
                .setId(module.getId())
                .build());

        proto.module.EmptyMessage response = moduleServiceStub
                .withDeadlineAfter(5, TimeUnit.SECONDS)
                .goodBye(request.build());

        if (response == null) {
            ModuleRemover.removeModule(module);
        }
    }

    private boolean moduleDoesNotExist(GrpcModule module) {
        Context context = Context.getInstance();

        try {
            context.findModuleById(module.getId());
        }
        catch (ModuleDoesNotExistException e) {
            System.out.println("Can not notify about id=" + module.getId());
            return true;
        }
        return false;
    }
}
