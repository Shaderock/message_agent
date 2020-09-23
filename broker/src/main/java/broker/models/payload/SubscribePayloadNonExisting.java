package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class SubscribePayloadNonExisting implements Payload {
    private final Code code;

    @JsonProperty("ids")
    private ArrayList<Integer> nonExistingModulesID;
}
