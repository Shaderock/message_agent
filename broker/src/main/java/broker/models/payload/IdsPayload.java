package broker.models.payload;

import lombok.Data;

@Data
public class IdsPayload implements Payload {
    private int[] ids;
}
