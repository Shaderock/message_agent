package broker.models;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class PortData {
    private int port;
    private final ArrayList<Module> modules = new ArrayList<>();

    public PortData(int port) {
        this.port = port;
    }
}
