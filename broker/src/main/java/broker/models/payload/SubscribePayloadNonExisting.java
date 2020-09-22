package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubscribePayloadNonExisting implements Payload{
    private final Code code;

    @JsonProperty("Non-Existing-Subscribers")
    private int[] ArrayNonExistingSubscribersID;
}
