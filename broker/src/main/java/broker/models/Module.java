package broker.models;

import broker.models.payload.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.Socket;

@Data
@AllArgsConstructor
public class Module {
    private Socket socket;
    private Type moduleType;
    private int id;
}
