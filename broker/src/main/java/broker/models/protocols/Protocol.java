package broker.models.protocols;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Protocol {
    private Operation operation;
}
