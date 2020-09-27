package broker.grpc.utils;

import broker.Context;
import broker.grpc.GrpcModule;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.module.EmptyMessage;
import proto.module.ModuleServiceGrpc;

public class ModuleRemover {
    public static synchronized void removeModule(GrpcModule module) {
        Context context = Context.getInstance();

        if (context.getGrpcModules().remove(module)) {
            if (context.APP_IS_SHUT_DOWN) {
                ManagedChannel channel = ManagedChannelBuilder
                        .forAddress(module.getIp(), module.getPort()).usePlaintext().build();
                ModuleServiceGrpc.ModuleServiceBlockingStub moduleServiceStub =
                        ModuleServiceGrpc.newBlockingStub(channel);

                proto.module.EmptyMessage.Builder request = EmptyMessage.newBuilder();
                proto.module.EmptyMessage response = moduleServiceStub.close(request.build());
            } else {
                new broker.grpc.utils.ModulesConnectionNotifier().notifyAboutModuleDisconnected(module);
            }
            System.out.println("Removed id=" + module.getId());
        }
    }
}
