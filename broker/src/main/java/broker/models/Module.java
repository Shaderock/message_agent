package broker.models;

import lombok.Data;

import java.net.Socket;

@Data
public class Module {
    private Socket socket;
    private String moduleName;
}
