package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class RedirectPayload implements Payload{
    private Code code;
    private int port;
}
