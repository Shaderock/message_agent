package broker.models.payload;

import lombok.Data;

@Data
public class NotifyPayload implements Payload {
    private String name;
    private String message;
}
