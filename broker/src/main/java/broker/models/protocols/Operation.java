package broker.models.protocols;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Operation {
    @JsonProperty("handshake")
    HANDSHAKE,
    @JsonProperty("get-modules")
    GET_MODULES,
    @JsonProperty("subscribe")
    SUBSCRIBE,
    @JsonProperty("notify")
    NOTIFY,
    @JsonProperty("direct-message")
    DIRECT_MESSAGE,
    @JsonProperty("close")
    CLOSE,
    @JsonProperty("welcome")
    WELCOME,
    @JsonProperty("good-bye")
    GOOD_BYE,
    @JsonProperty("keep-alive")
    KEEP_ALIVE,
    @JsonProperty("broker-is-alive")
    BROKER_IS_ALIVE,
    @JsonProperty("module-is-alive")
    MODULE_IS_ALIVE
}
