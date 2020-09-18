package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirectMessageRequestPayload implements Payload{
    @JsonProperty("id-receiver")
    private int idReceiver;

    private String command;

    @JsonProperty("info-block")
    private String infoBlock;
}
