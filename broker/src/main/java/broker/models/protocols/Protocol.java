package broker.models.protocols;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
//@JsonTypeInfo(use = NAME, include = PROPERTY)
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
//        include = JsonTypeInfo.As.PROPERTY)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = PayloadProtocol.class)
//})

//@JsonSubTypes({
//        @JsonSubTypes.Type(value = PayloadProtocol.class, name="payload")
//})
public class Protocol {
    private Operation operation;

//    public Protocol() {
//    }
}
