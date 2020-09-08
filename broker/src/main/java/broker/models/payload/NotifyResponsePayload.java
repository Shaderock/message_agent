package broker.models.payload;

import lombok.Data;

@Data
public class NotifyResponsePayload implements Payload{
    private String name;
    private String message;
}
