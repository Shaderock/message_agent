package broker.actions.requests;

import broker.models.Module;
import broker.models.payload.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ProtocolTaskExecutor {
    private Payload payload;

    public abstract void execute(Module module);
}
