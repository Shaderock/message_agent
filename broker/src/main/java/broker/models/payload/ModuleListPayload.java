package broker.models.payload;

import broker.models.Module;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ModuleListPayload implements Payload {
    private Code code;
    private final ArrayList<Module> modules = new ArrayList<>();
}
