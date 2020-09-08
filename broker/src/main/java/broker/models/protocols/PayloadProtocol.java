package broker.models.protocols;

import broker.models.payload.Payload;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadProtocol extends Protocol{
    private Payload payload;
}
