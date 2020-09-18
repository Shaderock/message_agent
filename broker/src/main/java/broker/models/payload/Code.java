package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Code {
    OK(20),
    REDIRECT(30),
    UNSUPPORTABLE_OPERATION(40),
    INCORRECT_PAYLOAD_SCHEME(41),
    NOT_ENOUGH_PLACE_FOR_NEW_CONNECTION(42),
    MODULE_DOES_NOT_EXIST(43),
    SELF_SUBSCRIBE(44);

    @Getter
    @JsonValue
    private final int code;

    Code(int code) {
        this.code = code;
    }
}
