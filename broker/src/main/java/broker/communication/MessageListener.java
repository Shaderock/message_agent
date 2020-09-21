package broker.communication;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MessageListener {
    public String listen(DataInputStream in) throws IOException {
        while (in.available() == 0) {
        }
        byte[] requestAsBytes = new byte[in.available()];
        int i = 0;
        while (in.available() != 0) {
            requestAsBytes[i++] = in.readByte();
        }

        return new String(requestAsBytes, StandardCharsets.UTF_8);
    }
}
