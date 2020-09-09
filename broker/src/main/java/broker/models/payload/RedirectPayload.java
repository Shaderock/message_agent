package broker.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RedirectPayload implements Payload {
    private Code code;
    private int port;
}
