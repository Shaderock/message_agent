package broker.models.payload;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

//@JsonTypeInfo(use = NAME, include = PROPERTY)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value=CodePayload.class),
//        @JsonSubTypes.Type(value=MessagePayload.class),
//        @JsonSubTypes.Type(value=ModuleListPayload.class),
//        @JsonSubTypes.Type(value=NamePayload.class),
//        @JsonSubTypes.Type(value=NotifyPayload.class),
//        @JsonSubTypes.Type(value=RedirectPayload.class),
//})
//@JsonSerialize()
public interface Payload {
}
