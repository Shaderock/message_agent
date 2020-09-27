package broker.utils;

import broker.Context;
import broker.exceptions.ModuleDoesNotExistException;
import broker.models.Module;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import proto.module.GoodByeRequest;
import proto.module.ModuleServiceGrpc;
import proto.module.WelcomeRequest;

import java.util.concurrent.TimeUnit;

public class ModulesConnectionNotifier {
    public void notifyAboutModuleConnected(Module module) {
        if (moduleDoesNotExist(module)) {
            return;
        }
        for (Module connectedModule : Context.getInstance().getModules()) {
            if (connectedModule.getId() != module.getId()) {
                ManagedChannel channel = ManagedChannelBuilder
                        .forAddress(connectedModule.getIp(), connectedModule.getPort()).usePlaintext().build();
                ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                        ModuleServiceGrpc.newBlockingStub(channel);

                proto.module.WelcomeRequest.Builder request = WelcomeRequest.newBuilder();
                request.setModule(proto.module.Module.newBuilder()
                        .setType(module.getType().getName())
                        .setId(module.getId())
                        .build());

                try {
                    proto.module.EmptyMessage response = moduleServiceStub
                            .withDeadlineAfter(5, TimeUnit.SECONDS)
                            .welcome(request.build());
                    System.out.println("SENT: " + request.toString());
                }
                catch (StatusRuntimeException e) {
                    System.out.println("Time for response has exceeded");
                    ModuleRemover.removeModule(connectedModule);
                }
            }
        }
    }

    public void notifyAboutModuleDisconnected(Module module) {
        for (Module connectedModule : Context.getInstance().getModules()) {
            if (connectedModule.getId() != module.getId()) {
                ManagedChannel channel = ManagedChannelBuilder
                        .forAddress(connectedModule.getIp(), connectedModule.getPort()).usePlaintext().build();
                ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                        ModuleServiceGrpc.newBlockingStub(channel);

                GoodByeRequest.Builder request = GoodByeRequest.newBuilder();
                request.setModule(proto.module.Module.newBuilder()
                        .setType(module.getType().getName())
                        .setId(module.getId())
                        .build());
                try {
                    proto.module.EmptyMessage response = moduleServiceStub
                            .withDeadlineAfter(5, TimeUnit.SECONDS)
                            .goodBye(request.build());
                    System.out.println("SENT: " + request.toString());
                }
                catch (StatusRuntimeException e) {
                    System.out.println("Time for response has exceeded");
                    ModuleRemover.removeModule(connectedModule);
                }
            }
        }
    }

    private boolean moduleDoesNotExist(Module module) {
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
