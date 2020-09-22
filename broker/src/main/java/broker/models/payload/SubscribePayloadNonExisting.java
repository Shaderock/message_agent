package broker.models.payload;

import lombok.Data;

@Data
public class SubscribePayloadNonExisting implements Payload{
    private final Code code;
    private int[] ArrayNonExistingSubscribersID;
}
