package broker.models.payload;

import lombok.Data;

@Data
public class NamePayload implements Payload {
    private String name;
}
