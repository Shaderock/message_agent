package broker.models.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class CodeIdsPayload implements Payload {
    private final Code code;

    private ArrayList<Integer> ids;
}
