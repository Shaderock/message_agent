package broker.communication;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MessageListener {
    @SuppressWarnings("BusyWait")
    public String listen(DataInputStream in) throws IOException {

        while (in.available() == 0) {
            try {
                Thread.sleep(25);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        byte[] requestAsBytes = new byte[in.available()];
        int i = 0;
        while (in.available() != 0) {
            requestAsBytes[i++] = in.readByte();
        }

        return new String(requestAsBytes, StandardCharsets.UTF_8);
    }
}
