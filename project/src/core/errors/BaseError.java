package core.errors;

import java.util.List;
import java.util.Objects;

public abstract class BaseError {
    private final String message;


    public BaseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}