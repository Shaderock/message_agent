package broker.models.payload;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ModuleListPayload implements Payload {
    private Code code;
    private final ArrayList<String> names = new ArrayList<>();
}
