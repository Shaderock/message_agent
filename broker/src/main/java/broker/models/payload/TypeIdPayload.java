package broker.models.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeIdPayload implements Payload{
    private Type type;
    private int id;
}
