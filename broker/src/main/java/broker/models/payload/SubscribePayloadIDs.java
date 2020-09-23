package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SubscribePayloadIDs implements Payload{

    @JsonProperty("ids")
    private int[] modulesIdsToSubscribeOn;
}
