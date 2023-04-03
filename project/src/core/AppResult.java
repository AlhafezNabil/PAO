package core;

import core.errors.BaseError;

public class AppResult<Data> {
    private final Data data;
    private final BaseError error;

    private AppResult(Data data, BaseError error) {
        assert data != null || error != null : "One of the parameters should not be null";
        this.data = data;
        this.error = error;
    }

    public AppResult(Data data) {
        this.data = data;
        this.error = null;
    }

    public AppResult(BaseError error) {
        this.data = null;
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public BaseError getError() {
        return error;
    }

    public boolean hasDataOnly() {
        return data != null && error == null;
    }

    public boolean hasErrorOnly() {
        return data == null && error != null;
    }

    public boolean hasDataAndError() {
        return data != null && error != null;
    }

}