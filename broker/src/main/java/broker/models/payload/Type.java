package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum Type {
    @JsonProperty("CR")
    CRYPTOGRAPHER(10),
    @JsonProperty("MNG")
    MANAGER(1);

    @Getter
    private final int maxConnections;

    Type(int maxConnections) {
        this.maxConnections = maxConnections;
    }
}
