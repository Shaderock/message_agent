package broker.models.payload;

import lombok.Data;

@Data
public class TypePayload implements Payload {
    private Type type;
}
