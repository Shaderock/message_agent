package broker.models.payload;

import lombok.Data;

import java.util.List;

@Data
public class SubscribePayloadIDs implements Payload{
    private int[] ArraySubscribersID;
}
