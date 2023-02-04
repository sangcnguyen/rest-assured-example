package core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class RequestWrapper<T> {

    private Map<String, String> headers;
    private Map<String, String> queryParameters;

    private T body;
}
