package broker.models;

import lombok.Data;

@Data
public class CommunicationMessageDTO {
    private Operation operation;

    private String payload;
}
