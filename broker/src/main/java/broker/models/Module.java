package broker.models;

import broker.models.payload.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Builder
public class Module {
    @JsonIgnore
    private Socket socket;
    @JsonIgnore
    private DataInputStream in;
    @JsonIgnore
    private PrintWriter out;
    @JsonIgnore
    private ArrayList<Integer> notifiersIds;
    @JsonIgnore
    private boolean connected;

    private Type type;
    private int id;
}
