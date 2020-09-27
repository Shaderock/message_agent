package broker.models.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Type {
    CR(10, "CR"),
    MNG(1, "MNG");

    @Getter
    private final int maxConnections;

    @Getter
    private final String name;
}
