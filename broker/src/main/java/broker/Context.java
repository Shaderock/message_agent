
package broker;

import broker.models.PortData;
import lombok.Getter;

import java.util.ArrayList;

public class Context {
    private static Context instance;

    @Getter
    private final ArrayList<PortData> portsData;

    @Getter
    private final int MAX_SOCKETS_PER_PORT = 5;

    private Context() {
        portsData = new ArrayList<PortData>();
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }
}
