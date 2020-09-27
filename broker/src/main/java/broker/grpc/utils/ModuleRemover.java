package broker.grpc.utils;

import broker.grpc.GrpcModule;

public class ModuleRemover {
    public void removeModule(long id) {
        // todo
    }

    public void removeModule(GrpcModule module) {
        removeModule(module.getId());
    }
}
