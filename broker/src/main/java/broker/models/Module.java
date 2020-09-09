package broker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.Socket;

@Data
@AllArgsConstructor
public class Module {
    private Socket socket;
    private String moduleName;
}
