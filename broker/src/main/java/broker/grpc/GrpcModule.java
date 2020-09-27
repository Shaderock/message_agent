package broker.grpc;

import broker.models.payload.Type;
import lombok.Data;

@Data
public class GrpcModule {
    private final int id;
    private final Type type;
}
