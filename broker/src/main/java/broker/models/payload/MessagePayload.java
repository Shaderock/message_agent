package broker.models.payload;

import lombok.Data;

@Data
public class MessagePayload implements Payload {
    private String message;
}
