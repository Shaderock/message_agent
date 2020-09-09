
package broker;

import broker.models.PortData;
import lombok.Getter;

import java.util.ArrayList;

public class Context {
    private static Context instance;

    public final int HANDSHAKE_PORT = 17001;
    public final int COMMUNICATION_PORT = 17002;
    public final int MAX_COMMUNICATION_PORTS = 3;

    @Getter
    private final ArrayList<PortData> portsData;

    public final int MAX_SOCKETS_PER_PORT = 10;

    private Context() {
        portsData = new ArrayList<>();
        for (int i = COMMUNICATION_PORT; i < COMMUNICATION_PORT + MAX_COMMUNICATION_PORTS; i++){
            portsData.add(new PortData(i, null));
            // todo проверять на наличие модуля внутри или по-другому
        }
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }
}
