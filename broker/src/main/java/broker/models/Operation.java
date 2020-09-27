package broker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Operation {
    @JsonProperty("broker-is-alive")
    BROKER_IS_ALIVE,
    @JsonProperty("module-is-alive")
    MODULE_IS_ALIVE
}
