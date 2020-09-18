package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirectMessageResponsePayload implements Payload{
    @JsonProperty("id-sender")
    private int idSender;

    private String command;

    @JsonProperty("info-block")
    private String infoBlock;
}
