package broker.grpc;

import broker.models.payload.Type;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GrpcModule {
    private final long id;
    private final Type type;
    private final String ip;
    private final int port;
    private ArrayList<Long> notifiersId = new ArrayList<>();
}
