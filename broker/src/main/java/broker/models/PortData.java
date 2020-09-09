package broker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class PortData {
    private int port;
    private ArrayList<Module> modules;
}
