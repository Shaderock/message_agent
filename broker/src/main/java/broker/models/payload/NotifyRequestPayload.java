package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NotifyRequestPayload implements Payload{

    private String command;

    @JsonProperty("info-block")
    private String infoBlock;
}