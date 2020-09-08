package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CodePayload.class),
        @JsonSubTypes.Type(value = ModuleListPayload.class),
        @JsonSubTypes.Type(value = NamePayload.class),
        @JsonSubTypes.Type(value = NotifyRequestPayload.class),
        @JsonSubTypes.Type(value = NotifyResponsePayload.class),
        @JsonSubTypes.Type(value = RedirectPayload.class)
})
public interface Payload {
}
