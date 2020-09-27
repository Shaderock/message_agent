package broker.utils;

import broker.Context;
import broker.models.Module;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.module.EmptyMessage;
import proto.module.ModuleServiceGrpc;

public class ModuleRemover {
    public static synchronized void removeModule(Module module) {
        Context context = Context.getInstance();

        if (context.getModules().remove(module)) {
            if (context.APP_IS_SHUT_DOWN) {
                ManagedChannel channel = ManagedChannelBuilder
                        .forAddress(module.getIp(), module.getPort()).usePlaintext().build();
//                ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
//                        ModuleServiceGrpc.newBlockingStub(channel);
                ModuleServiceGrpc.ModuleServiceStub moduleServiceStub =
                        ModuleServiceGrpc.newStub(channel);

                proto.module.EmptyMessage.Builder request = EmptyMessage.newBuilder();
                EmptyMessage response = request.build();
                System.out.println("gRPC SENT to id=" + module.getId() +
                        ": empty message");
                //noinspection ResultOfMethodCallIgnored
                moduleServiceStub.close(response, null); // todo handle ignoring?
            } else {
                new ModulesConnectionNotifier().notifyAboutModuleDisconnected(module);
            }
            System.out.println("Removed id=" + module.getId());
        }
    }
}
