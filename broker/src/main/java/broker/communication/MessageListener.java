package broker.communication;

import broker.models.Module;
import lombok.RequiredArgsConstructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class MessageListener {
    private final Module module;

    @SuppressWarnings("BusyWait")
    public String listen() throws IOException, InterruptedException {
        DataInputStream in = module.getIn();

        while (in.available() == 0) {
            Thread.sleep(1);
        }

        byte[] requestAsBytes = new byte[in.available()];
        int i = 0;
        while (in.available() != 0) {
            requestAsBytes[i++] = in.readByte();
        }

        return new String(requestAsBytes, StandardCharsets.UTF_8);

    }
}
