package broker.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PortData {
    private int port;
    private ArrayList<Module> modules;
}
