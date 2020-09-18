package broker.models;

import broker.models.payload.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;

@Data
@AllArgsConstructor
public class Module {
    @JsonIgnore
    private Socket socket;
    @JsonIgnore
    private DataInputStream in;
    @JsonIgnore
    private PrintWriter out;

    private Type type;
    private int id;
}
