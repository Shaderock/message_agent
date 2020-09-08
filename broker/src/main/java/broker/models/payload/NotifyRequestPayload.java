package broker.models.payload;

import lombok.Data;

@Data
public class NotifyRequestPayload implements Payload{
    private String message;
}
