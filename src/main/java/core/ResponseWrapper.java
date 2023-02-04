package core;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {
    private T body;
    private Response responseRaw;

    public int getStatusCode() {
        return responseRaw.getStatusCode();
    }
}

