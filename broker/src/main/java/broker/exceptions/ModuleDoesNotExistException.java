package broker.exceptions;

import lombok.Getter;

public class ModuleDoesNotExistException extends Exception {
    @Getter
    private final long id;

    public ModuleDoesNotExistException(String message, long id) {
        super(message);
        this.id = id;
    }
}
