package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Code {
    @JsonProperty("20")
    OK,
    @JsonProperty("30")
    REDIRECT,
    @JsonProperty("40")
    UNSUPPORTABLE_OPERATION,
    @JsonProperty("41")
    INCORRECT_PAYLOAD_SCHEME,
    @JsonProperty("42")
    NAME_ALREADY_EXISTS,
    @JsonProperty("43")
    NOME_DOES_NOT_EXIST,
    @JsonProperty("44")
    SELF_SUBSCRIBE
}