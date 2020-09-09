package broker.models.payload;

import lombok.Data;

@Data
public class CodePayload implements Payload {
    private final Code code;
}
